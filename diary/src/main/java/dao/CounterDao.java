package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Counter;


public class CounterDao {
	
	//오늘 날짜의 카운트가 존재하는지
	//오늘 접속자 수
	public int selectCounterByToday() {
		//SELECT * FROM counter
		//WHERE cnt_date = CURRENT_DATE
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int todayCnt=0;
		
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
							SELECT * FROM counter
						    WHERE cnt_date = CURRENT_DATE
						""";
			stmt = conn.prepareStatement(sql);
			//쿼리문 디버깅
			System.out.println("selectCounterByToday : " +stmt);
			rs = stmt.executeQuery();
			 if (rs.next()) {
		        todayCnt = rs.getInt("cnt_num");
		        System.out.println("오늘 접속자 수 : " + todayCnt);
		        }else {
		        	todayCnt = 0;
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
		return todayCnt;
	}
	
	//selectCounterByToday 가 없을 때
	public int insertCounter() {
		//INSERT INTO counter(cnt_date, cnt_num)
		//VALUES(CURDATE(),1)
		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
							INSERT INTO counter(cnt_date, cnt_num)
						    VALUES(CURRENT_DATE,1)
						""";
			stmt = conn.prepareStatement(sql);
			//쿼리문 디버깅
			System.out.println("insertCounter : " +stmt);
			
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
	
	//selectCounterByToday 가 있을 때
	public int updateCounter() {
		//UPDATE counter SET cnt_num = cnt+1
		//WHERE cnt_date = CURRENT_DATE

		Connection conn = null;
		PreparedStatement stmt = null;
		int row = 0;
		
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			
			String sql = """
							UPDATE counter SET cnt_num = cnt_num+1
						    WHERE cnt_date = CURRENT_DATE
						""";
			stmt = conn.prepareStatement(sql);
			//쿼리문 디버깅
			System.out.println("updateCounter : " +stmt);
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
	
	//누적 접속자 수
	public int selectCounterSum() {
		//SELECT SUM(cnt_num) FROM counter
		int sumCounter = 0;
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
							SELECT SUM(cnt_num) FROM counter
						""";
			stmt = conn.prepareStatement(sql);
			//쿼리문 디버깅
			System.out.println("selectCounterSum : " +stmt);
			rs = stmt.executeQuery();
			
			 if (rs.next()) {
				 sumCounter = rs.getInt(1);
		            System.out.println("누적 접속자 수 : " + sumCounter);
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
		
		return sumCounter;
	}
}
