package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Comment;
import vo.Notice;

public class CommentDao {
	//comment 추가
	/*
	 INSERT INTO comment(notice_no, member_id, comment_content ,is_secret)
			VALUE(?,?,?,?)
	 */
   public int insertComment(Comment comment) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
					INSERT INTO comment(notice_no, member_id, comment_content ,is_secret)
							VALUE(?,?,?,?)
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getNoticeNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.setInt(4, comment.getIsSecret());

			//쿼리문 디버깅
			System.out.println("insertScheduleByDay : " +stmt);
			row = stmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return row;
   }
  
   //comment 삭제
	/*
	// comment의 작성자가 로그인계정과 동일하고 비밀번호가 같은 comment가 있다면
	1. SELECT COUNT(comment_no)
		FROM comment c INNER JOIN member m
		ON c.member_id = m.member_id
		WHERE c.member_id = ? 
		AND c.comment_no = ?
		AND m.member_pw = PASSWORD(?)
	// 관리자 비밀번호 일치 쿼리
	2. 	SELECT COUNT(member_no)
		FROM member
		WHERE member_id = ? 
		AND member_pw = PASSWORD(?)
	// comment 삭제 쿼리
	3. DELETE FROM comment WHERE comment_no = ?
	 */
   // 글쓴이 or member_level > 0
   public int deleteComment(int commentNo, String password, String memberId, int memberLevel) {
	   //요청성공 디버깅 변수
	   int row = 0;
	   //member_level이 1이 아닐경우 작성자 확인 변수
	   int sameMember = 0;
	   Connection conn = null;
	   PreparedStatement stmt1 = null;
	   PreparedStatement stmt2 = null;
	   ResultSet rs1 = null;
	   ResultSet rs2 = null;
		
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			if(memberLevel != 1) {
				String sql1 = """
								SELECT COUNT(comment_no)
								FROM comment c INNER JOIN member m
								ON c.member_id = m.member_id
								WHERE c.member_id = ? 
								AND c.comment_no = ?
								AND m.member_pw = PASSWORD(?)
							""";
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setString(1, memberId);
				stmt1.setInt(2, commentNo);
				stmt1.setString(3, password);
				//쿼리문 디버깅
				System.out.println("comment삭제를 위한 작성자 비밀번호 확인 쿼리 : " +stmt1);
				rs1 = stmt1.executeQuery();
				if (rs1.next()) {
					sameMember = rs1.getInt(1);
					System.out.println(sameMember + " 1 -> 작성자 일치 0-> 작성자 불일치 ");
				}
			}else {
				String sql1 = """
								SELECT COUNT(member_no)
								FROM member
								WHERE member_id = ? 
								AND member_pw = PASSWORD(?)
							""";
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setString(1, memberId);
				stmt1.setString(2, password);
				//쿼리문 디버깅
				System.out.println("comment삭제를 위한 관리자 비밀번호 확인 쿼리 : " +stmt1);
				rs2 = stmt1.executeQuery();
				if (rs2.next()) {
					memberLevel = rs2.getInt(1);
				     System.out.println(memberLevel + " 1 -> 관리자 패스워드 일치 0-> 불일치 ");
				}		
			}
			
			
			if(memberLevel == 1 || sameMember == 1){
				String sql2 = """
								DELETE FROM comment WHERE comment_no = ?
						""";
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, commentNo);

				//쿼리문 디버깅
				System.out.println("deleteComment 쿼리 : " +stmt2);				
				row = stmt2.executeUpdate();
			}else {
				//stmt2가 특정조건에 의해 생성되므로 초기화해준다
				stmt2 = null;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt1.close();
				//stmt1은 조건문에 의해 무조건 생성이 되지만 stmt2는 생성이 안될수 있다 
				  if (stmt2 != null) {
			            stmt2.close();
			        }
				  if (rs1 != null) {
					  rs1.close();
				  }
				  if (rs2 != null) {
					  rs2.close();
				  }
				conn.close();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
				
		return row;	   
   }
   
   
   //comment List 출력
   /*
    	1. memberLevel이 1일경우 모든 comment가 출력
    	SELECT	
    			comment_no commentNo,
				member_id memberId,
				comment_content commentContent,
				is_secret isSecret,
				createdate
		FROM comment
		WHERE notice_no = ?
		order BY createdate DESC;
		
		2. memberLevel이 0일경우 is_secret == false and 본인이 작성한 comment만 출력
		SELECT
				comment_no commentNo,
				member_id memberId,
				createdate,
				is_secret isSecret,
  			CASE
    			WHEN is_secret = 0 OR member_id = ? THEN comment_content
    			ELSE '비밀글입니다.'
  				END AS commentContent
		FROM comment
		WHERE notice_no = ?
    */
   
   // 비밀글 : 본인 or member_level > 0, 본인/관리자 아닌경우 view에서 '비밀글입니다'
   public List<Comment> selectCommentList(Map<String, Object> map) {
	   
	   	int memberLevel = (int)map.get("memberLevel");
	   	String memberId = (String)map.get("memberId");
	   	int noticeNo = (int)map.get("noticeNo");
	   	
	   	ArrayList<Comment> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			if(memberLevel == 1) {
				String sql = """
							SELECT	
									comment_no commentNo,
									member_id memberId,
									comment_content commentContent,
									is_secret isSecret,
									createdate
							FROM comment
							WHERE notice_no = ?
							order BY createdate DESC;
							""";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, noticeNo);
				//쿼리문 디버깅
				System.out.println("selectCommentList : " +stmt);
				rs = stmt.executeQuery();
			}else {
				String sql = """
							SELECT
									comment_no commentNo,
									member_id memberId,
									createdate,
									is_secret isSecret,
								CASE
									WHEN is_secret = 0 OR member_id = ? THEN comment_content
									ELSE '&#x1F512; 비밀글입니다.'
									END AS commentContent
							FROM comment
							WHERE notice_no = ?
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, noticeNo);
			
			//쿼리문 디버깅
			System.out.println("selectCommentList : " +stmt);
			rs = stmt.executeQuery();
			}
			  while(rs.next()) {
		           	Comment comment = new Comment();
		           	comment.setCommentNo(rs.getInt("commentNo"));
		           	comment.setMemberId(rs.getString("memberId"));
		           	comment.setCommentContent(rs.getString("commentContent"));
		           	comment.setCreatedate(rs.getString("createdate"));
		           	comment.setIsSecret(rs.getInt("isSecret"));
		            list.add(comment);
		         }	
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return list;
   }
   
   // comment 수정, 삭제를 위한 상세보기
   /*
		SELECT
			c.notice_no noticeNo,
			c.member_id memberId,
			c.comment_content commentContent,
			c.comment_no commentNo,
			c.is_secret isSecret,
			c.createdate createdate,
			n.notice_title noticeTitle,
			n.notice_content noticeContent
		FROM comment c INNER JOIN notice n
		ON c.notice_no = n.notice_no
		WHERE comment_no = ?
    */
   public Map<String,Object> selectCommentOne(int commentNo) {
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   Map<String,Object> map = new HashMap<String,Object>();
	   try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
						   SELECT
								c.notice_no noticeNo,
								c.comment_no commentNo,
								c.member_id memberId,
								c.comment_content commentContent,
								c.comment_no commentNo,
								c.is_secret isSecret,
								c.createdate createdate,
								n.notice_title noticeTitle,
								n.notice_content noticeContent
							FROM comment c INNER JOIN notice n
							ON c.notice_no = n.notice_no
							WHERE comment_no = ?
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, commentNo);
			//쿼리문 디버깅
			System.out.println("selectCommentOne : " +stmt);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				map.put("noticeNo", rs.getObject("noticeNo"));
				map.put("commentNo", rs.getObject("commentNo"));
				map.put("memberId", rs.getObject("memberId"));
				map.put("commentContent", rs.getObject("commentContent"));
				map.put("createdate", rs.getObject("createdate"));
				map.put("noticeTitle", rs.getObject("noticeTitle"));
				map.put("noticeContent", rs.getObject("noticeContent"));
			}
			
			System.out.println(map);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	      
      return map;
   }


   // 작성자 본인만 comment 수정가능
   /*
	1. SELECT 
			c.comment_no commentNo
		FROM comment c INNER JOIN member m
		ON m.member_id = c.member_id
		WHERE c.comment_no = ?
		AND c.member_id =?
		AND c.notice_no =?
		AND m.member_pw = PASSWORD(?)
	
	2. UPDATE comment
		SET comment_content = ?,
		is_secret = ?
		WHERE comment_no = ?
*/
   public int updateComment(Map<String,Object> map) {
	   //요청성공 디버깅 변수
	   int row = 0;
	   //수정할 댓글 번호
	   int updateCommentNo = 0;
	   
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
		
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql1 = """
							SELECT c.comment_no commentNo
								FROM comment c INNER JOIN member m
								ON m.member_id = c.member_id
								WHERE c.comment_no = ?
								AND c.member_id =?
								AND c.notice_no =?
								AND m.member_pw = PASSWORD(?)
						""";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, (int)map.get("commentNo"));
			stmt.setString(2, (String)map.get("memberId"));
			stmt.setInt(3, (int)map.get("noticeNo"));
			stmt.setString(4, (String)map.get("password"));
			//쿼리문 디버깅
			System.out.println("수정할 댓글 번호 select 쿼리 : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				updateCommentNo = rs.getInt(1);
				System.out.println("수정할 댓글 번호 : " + updateCommentNo);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				if (rs != null) {
				    rs.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(updateCommentNo != 0) {
			try {
				// Tomcat context.xml 설정을 로드
				Context context = new InitialContext();
				// context.xml에서 커넥션풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				
				String sql2 = """
						UPDATE comment
							SET comment_content = ?,
							is_secret = ?
							WHERE comment_no = ?
							""";
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, (String)map.get("commentContent"));
				stmt.setInt(2, (int)map.get("secret"));
				stmt.setInt(3, (int)map.get("commentNo"));
				
				//쿼리문 디버깅
				System.out.println("updateNotice : " +stmt);
				row = stmt.executeUpdate();
	
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return row;	   
   }
   
}