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

import vo.Schedule;

public class ScheduleDao {
	
	//일별 스케줄 추가
	/*
	 	INSERT INTO SCHEDULE(member_id, schedul_date, schedul_memo, schedule_emoji, createdate)
					VALUE(?,?,?,?,NOW())
	 */
	public int insertScheduleByDay(Schedule schedule){
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
						INSERT INTO SCHEDULE(member_id, schedule_date, schedule_memo, createdate)
									VALUE(?,?,?,NOW())
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, schedule.getMemeberId());
			stmt.setString(2, schedule.getScheduleDate());
			stmt.setString(3, schedule.getScheduleMemo());

			
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
	
	//일별 스케줄 삭제
	/*
 		DELETE 
		FROM SCHEDULE 
		WHERE member_id = ? 
		AND schedule_no = ?
	 */
	public int deleteScheduleByDay(String memberId, int scheduleNo){
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
						DELETE 
						FROM SCHEDULE 
						WHERE member_id = ? 
						AND schedule_no = ?
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, scheduleNo);
			//쿼리문 디버깅
			System.out.println("deleteScheduleByDay : " +stmt);
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
	
	
	//월별 사용자 스케줄
	/*
	  SELECT
		DAY(schedule_date) scheduleDay,
		COUNT(*) cnt,
		GROUP_CONCAT(substr(schedule_memo, 1, 5)SEPARATOR '\n') memo
	  FROM schedule
	  WHERE member_id = ? 
	  AND YEAR(schedule_date) = ?
	  AND MONTH(schedule_date) = ?
	  GROUP BY schedule_date
	  ORDER BY schedule_date ASC
	 */
	public List<Map<String,Object>> selectScheduleByMonth(String memberId, int year, int month){
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
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			
			String sql = """
						SELECT
							DAY(schedule_date) scheduleDay,
							COUNT(*) cnt,
							GROUP_CONCAT(substr(schedule_memo, 1, 5)SEPARATOR '\n') memo
						FROM schedule
						WHERE member_id = ? 
						AND YEAR(schedule_date) = ?
						AND MONTH(schedule_date) = ?
						GROUP BY schedule_date
						ORDER BY schedule_date ASC
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, year);
			stmt.setInt(3, month);
			//쿼리문 디버깅
			System.out.println("selectScheduleByMonth : " +stmt);
			rs = stmt.executeQuery();
			  while(rs.next()) {
		            Map<String, Object> m = new HashMap<>();
		            m.put("scheduleDay", rs.getString("scheduleDay"));
		            m.put("cnt", rs.getInt("cnt"));
		            m.put("memo", rs.getString("memo"));
		            list.add(m);
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
	
	//일별 사용자 스케줄 리스트 출력
	/*
	   SELECT 
			member_id memberId, 
			YEAR(schedule_date) scheduleYear,
			MONTH(schedule_date) scheduleMonth, 
			DAY(schedule_date) scheduleDay, 
			schedule_memo memo, 
			schedule_no scheduleNo, 
			createdate
		FROM SCHEDULE
		WHERE member_id = ?
		AND YEAR(schedule_date) =?
		AND MONTH(schedule_date) = ?
		AND DAY(schedule_date) =?
		ORDER BY schedule_no DESC;
	 */
	public List<Map<String,Object>> selectScheduleByDay(String memberId, int year, int month, int day){
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
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			
			String sql = """
							SELECT 
								member_id memberId, 
								YEAR(schedule_date) scheduleYear,
								MONTH(schedule_date) scheduleMonth, 
								DAY(schedule_date) scheduleDay, 
								schedule_memo memo, 
								schedule_no scheduleNo, 
								createdate
							FROM SCHEDULE
							WHERE member_id = ?
						AND YEAR(schedule_date) =?
						AND MONTH(schedule_date) = ?
						AND DAY(schedule_date) =?
						ORDER BY schedule_no DESC;
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, year);
			stmt.setInt(3, month);
			stmt.setInt(4, day);
			//쿼리문 디버깅
			System.out.println("selectScheduleByDay : " +stmt);
			rs = stmt.executeQuery();
			  while(rs.next()) {
		            Map<String, Object> m = new HashMap<>();
		            m.put("memberId", rs.getString("memberId"));
		            m.put("scheduleYear", rs.getInt("scheduleYear"));
		            m.put("scheduleMonth", rs.getInt("scheduleMonth"));
		            m.put("scheduleDay", rs.getInt("scheduleDay"));
		            m.put("memo", rs.getString("memo"));
		            m.put("scheduleNo", rs.getInt("scheduleNo"));
		            m.put("createdate", rs.getString("createdate"));

		            list.add(m);
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
	

	//스케줄 출력
	/*
	   SELECT 
			member_id memberId,
			schedule_date scheduleDate,
			schedule_memo scheduleMemo,
			createdate 
		FROM schedule
		WHERE schedule_no = ?
	 */
	public Schedule selectScheduleOne(int scheduleNo){
		Schedule schedule = new Schedule();
		
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
							   SELECT 
									member_id memberId,
									schedule_date scheduleDate,
									schedule_memo scheduleMemo,
									createdate 
								FROM schedule
								WHERE schedule_no = ?
						""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scheduleNo);

			//쿼리문 디버깅
			System.out.println("selectScheduleByDay : " +stmt);
			rs = stmt.executeQuery();
			  while(rs.next()) {
		           schedule.setMemeberId(rs.getNString("memberId"));
		           schedule.setScheduleDate(rs.getString("scheduleDate"));
		           schedule.setScheduleMemo(rs.getString("scheduleMemo"));
		           schedule.setCreatedate("createdate");
		         }
			  schedule.setScheduleNo(scheduleNo);
			
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
		
		return schedule;
		}


	//일정 수정
	/*
	  	UPDATE schedule SET schedule_memo = ? WHERE schedule_no = ? 
	 */
	public int updateSchedule(Schedule schedule) {
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
			String sql = "UPDATE schedule SET schedule_memo = ? WHERE schedule_no = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,schedule.getScheduleMemo() );
			stmt.setInt(2,schedule.getScheduleNo());

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
	

	
}
