package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Member;

public class MemberDao {
	
	//비밀번호 변경
	/*
	  	비밀번호 변경
	  	UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? AND member_ps = PASSWORD(?)
	 */
	public int updateMember(String memberId, String memberPw, String newMemberPw) {
		int row = 0;
		//커넥션풀
		//자원해제를 위해서 try...catch문 밖에 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id = ? AND member_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,newMemberPw );
			stmt.setString(2,memberId);
			stmt.setString(3,memberPw);
			System.out.println("ModifyPwQuery : " +stmt);
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
	
	
	//회원탈퇴
	/*
	  	멤버삭제
	  	DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)
	 */
	public int deleteMember(Member paramMember) {
		int row = 0;
		//커넥션풀
		//자원해제를 위해서 try...catch문 밖에 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			System.out.println("DeleteMemberQuery : " +stmt);
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
	
	
	//로그인
	/*
	  	SELECT member_no memberNo, member_id memberId
			FROM diary
			WHERE member_id = ? AND member_pw = PASSWORD(?);
	 */
	public Member loginMember(Member paramMember) {
		Member resultMember = null;
		//커넥션풀
		//자원해제를 위해서 try...catch문 밖에 생성
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
			String sql = """
					SELECT member_no memberNo, member_id memberId
						FROM member
						WHERE member_id = ? AND member_pw = PASSWORD(?)
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			System.out.println("LoginQuery : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				resultMember = new Member();
				resultMember.setMemberId(rs.getString("memberId"));
				resultMember.setMemberNo(rs.getInt("memberNo"));
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
		
		return resultMember; //로그인 실패시 null, 성공시 memberNo속성+memberId속성
	}
	
	
	//회원가입
	/*
	 * INSERT INTO member(member_id, member_pw) VALUE(?,PASSWORD(?))
	 */
	
	public int insertMember(Member paramMember) {
		int row = 0;
		//커넥션풀
		//자원해제를 위해서 try...catch문 밖에 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			String sql = "INSERT INTO member(member_id, member_pw) VALUES(?,PASSWORD(?))";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			System.out.println("AddMemberQuery : " +stmt);
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

	//로그인한 아이디의 level이 1이면 매니져 0이면 고객
	/*
	  	SELECT member_level memberLevel
			FROM member
			WHERE member_id = ?
	 */
	public int levelMember(String memberId) {
		int memberLevel = 0;
		//커넥션풀
		//자원해제를 위해서 try...catch문 밖에 생성
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
			String sql = """
					SELECT member_level memberLevel
						FROM member
						WHERE member_id = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			System.out.println("LoginQuery : " +stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				memberLevel = rs.getInt(1);
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
		
		return memberLevel; //아이디의 레벨 리턴
	}
}
