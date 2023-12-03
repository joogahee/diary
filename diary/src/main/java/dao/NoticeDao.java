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

import vo.Notice;

public class NoticeDao {
   // member_level > 0 관리자만 작성 가능 
	/*
	  	1. SELECT member_level
			from member
			WHERE member_id = ?
			
	 	2. INSERT INTO notice(notice_title, notice_content, member_id , createdate)
					VALUE(?,?,?,NOW())
	 */
   public int insertNotice(Notice notice) {
	   //요청성공 디버깅 변수
	   int row = 0;
	   //레벨 저장 변수
	   int level = 0;
	   
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
						SELECT member_level
							from member
							WHERE member_id = ?
						""";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, notice.getMemberId());
			//쿼리문 디버깅
			System.out.println("selectLevel : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				level = rs.getInt(1);
				System.out.println("level : " + level);
			}

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
		
		if(level > 0) {
			try {
				// Tomcat context.xml 설정을 로드
				Context context = new InitialContext();
				// context.xml에서 커넥션풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				
				String sql2 = """
							INSERT INTO notice(notice_title, notice_content, member_id )
										VALUE(?,?,?)
							""";
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, notice.getNoticeTitle());
				stmt.setString(2, notice.getNoticeContent());
				stmt.setString(3, notice.getMemberId());
	
				
				//쿼리문 디버깅
				System.out.println("insertNotice : " +stmt);
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
   
   // member_level > 0 and pw일치 공지 삭제
	/*
 		1. SELECT n.notice_no noticeNo
				FROM notice n INNER JOIN member m
				ON n.member_id = m.member_id
				WHERE n.notice_no = ?
				AND n.member_id = ?
				AND m.member_pw = password(?)
		
		2. INSERT INTO notice(notice_title, notice_content, memberid , createdate)
				VALUE(?,?,?,NOW())
	 */
   public int deleteNotice(int noticeNo, String password, String memberId) {
	   //요청성공 디버깅 변수
	   int row = 0;
	   //삭제할 공지 번호
	   int deleteNoticeNo = 0;
	   
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
							SELECT n.notice_no noticeNo
								FROM notice n INNER JOIN member m
								ON n.member_id = m.member_id
								WHERE n.notice_no = ?
								AND n.member_id = ?
								AND m.member_pw = password(?)
						""";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, noticeNo);
			stmt.setString(2, memberId);
			stmt.setString(3, password);
			//쿼리문 디버깅
			System.out.println("deleteNoticeNo 쿼리 : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				deleteNoticeNo = rs.getInt(1);
				System.out.println("삭제할 공지 번호 : " + deleteNoticeNo);
			}

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
		
		if(deleteNoticeNo != 0) {
			try {
				// Tomcat context.xml 설정을 로드
				Context context = new InitialContext();
				// context.xml에서 커넥션풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				
				String sql2 = """
							DELETE FROM notice WHERE notice_no = ?
							""";
				stmt = conn.prepareStatement(sql2);
				stmt.setInt(1, deleteNoticeNo);
				
				//쿼리문 디버깅
				System.out.println("deleteNotice : " +stmt);
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
   
   // member_level > 0 and pw일치 공지 수정
   /*
	1. SELECT n.notice_no noticeNo
			FROM notice n INNER JOIN member m
			ON n.member_id = m.member_id
			WHERE n.notice_no = ?
			AND n.member_id = ?
			AND m.member_pw = password(?)
	
	2. UPDATE notice
		SET notice_content = ?,
		notice_title = ?
		WHERE notice_no = ?
*/
   public int updateNotice(Notice notice, String password) {
	   //요청성공 디버깅 변수
	   int row = 0;
	   //수정할 공지 번호
	   int updateNoticeNo = 0;
	   
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
							SELECT n.notice_no noticeNo
								FROM notice n INNER JOIN member m
								ON n.member_id = m.member_id
								WHERE n.notice_no = ?
								AND n.member_id = ?
								AND m.member_pw = password(?)
						""";
			stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, notice.getNoticeNo());
			stmt.setString(2, notice.getMemberId());
			stmt.setString(3, password);
			//쿼리문 디버깅
			System.out.println("updateNoticeNo 쿼리 : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				updateNoticeNo = rs.getInt(1);
				System.out.println("수정할 공지 번호 : " + updateNoticeNo);
			}

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
		
		if(updateNoticeNo != 0) {
			try {
				// Tomcat context.xml 설정을 로드
				Context context = new InitialContext();
				// context.xml에서 커넥션풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				
				String sql2 = """
						UPDATE notice
							SET notice_content = ?,
							notice_title = ?
							WHERE notice_no = ?
							""";
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, notice.getNoticeContent());
				stmt.setString(2, notice.getNoticeTitle());
				stmt.setInt(3, updateNoticeNo);
				
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
   
   // notice 상세보기
   /*
   	    SELECT n.notice_no noticeNo, 
			n.notice_title noticeTitle, 
			n.notice_content noticeContent, 
			n.member_id memberId, 
	    	n.createdate createdate
		FROM notice n 
		WHERE notice_no = ?
		ORDER BY n.createdate desc
    */
   public Notice selectNoticeOne(int noticeNo) {
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   Notice notice = null;
	   try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
						    SELECT n.notice_no noticeNo, 
								n.notice_title noticeTitle, 
								n.notice_content noticeContent, 
								n.member_id memberId, 
							    n.createdate createdate
							FROM notice n 
							WHERE notice_no = ?
							ORDER BY n.createdate desc
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeNo);
			//쿼리문 디버깅
			System.out.println("selectOneNotice : " +stmt);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
			notice = new Notice();
			notice.setNoticeNo(noticeNo);
			notice.setNoticeTitle(rs.getString("noticeTitle"));
			notice.setNoticeContent(rs.getString("noticeContent"));
			notice.setMemberId(rs.getString("memberId"));
			notice.setCreatedate(rs.getString("createdate"));
			}
			
			System.out.println(notice);
			
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
	      
      return notice;
   }
   
   //memberHome.jsp 에 공지리스트출력
   /*
   	 SELECT n.notice_no noticeNo, 
		n.notice_title noticeTitle,  
		n.member_id memberId, 
	    n.createdate,
	    m.member_level memberLevel
	FROM notice n inner join member m
	ON m.member_id = n.member_id
	ORDER BY n.createdate desc
    */
   public List<Map<String,Object>> selectNoticeList() {
	   List<Map<String,Object>> list = new ArrayList<>();
	   
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
						    SELECT n.notice_no noticeNo, 
								n.notice_title noticeTitle, 
								n.member_id memberId, 
							    n.createdate,
							    m.member_level memberLevel
							FROM notice n inner join member m
							ON m.member_id = n.member_id
							ORDER BY n.createdate desc
						""";
			stmt = conn.prepareStatement(sql);
			//쿼리문 디버깅
			System.out.println("selectNoticeList : " +stmt);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("noticeNo", rs.getInt("noticeNo"));
				m.put("noticeTitle", rs.getString("noticeTitle"));
				m.put("memberId", rs.getString("memberId"));
				m.put("createdate", rs.getString("createdate"));
				m.put("memberLevel", rs.getString("memberLevel"));
				
				list.add(m);
			}

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
	   
      return list;
   }
}