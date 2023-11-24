package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;
import vo.Schedule;

@WebServlet("/schedule/addSchedule")
public class AddScheduleController extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
		}
		
		//요청분석
		int year = Integer.parseInt(request.getParameter("targetY"));
		int month = Integer.parseInt(request.getParameter("targetM"));
		int day = Integer.parseInt(request.getParameter("targetD"));
		String memo = request.getParameter("scheduleMemo");
		String date = year + "-" + month + "-" + day;
		
		//디버깅
		System.out.println(date + "일정 추가 날짜");
		System.out.println(memo + "일정 추가 내용");
		
		Member member= (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		
		Schedule schedule = new Schedule();
		schedule.setScheduleMemo(memo);
		schedule.setMemeberId(memberId);
		schedule.setScheduleDate(date);
		
		//디버깅
		System.out.println(schedule);
		
		ScheduleDao scheduleDao = new ScheduleDao();
		int row = scheduleDao.insertScheduleByDay(schedule);
		
		//요청 성공 디버깅
		if(row != 1) {
			System.out.println("일정추가 실패");
		}else {
			System.out.println("일정추가 성공");
		}
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/member/memberHome.jsp").forward(request, response);
		
	}

}
