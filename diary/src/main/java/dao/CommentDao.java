package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Comment;

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
			stmt.setBoolean(4, comment.isSecret());

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
   
   // 글쓴이 or member_level > 0
   public int deleteComment(Comment comment) {
      return 0;
   }
   // 글쓴이
   public int updateComment(Comment comment) {
      return 0;
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
  			CASE
    			WHEN is_secret = false OR member_id = ? THEN comment_content
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
								CASE
									WHEN is_secret = false OR member_id = ? THEN comment_content
									ELSE '비밀글입니다.'
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
}