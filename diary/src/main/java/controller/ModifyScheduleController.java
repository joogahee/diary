package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Schedule;

@WebServlet("/schedule/modifySchedule")
public class ModifyScheduleController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
		String targetY = request.getParameter("targetY");
		String targetM = request.getParameter("targetM");
		String targetD = request.getParameter("targetD");
		
		//modifySchedule.jsp에 수정 전 값 출력을 위해 Dao 호출
		ScheduleDao scheduleDao = new ScheduleDao();
		Schedule modifySchedule = scheduleDao.selectScheduleOne(scheduleNo);
		
		//수정할 schedule 디버깅
		System.out.println(modifySchedule + "수정 할 schedule객체");
		
		//출력을 위해 request영역에 담기
		request.setAttribute("schedule", modifySchedule);
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/schedule/modifySchedule.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청분석
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
		String scheduleMemo = request.getParameter("scheduleMemo");
		String targetY = request.getParameter("targetY");
		String targetM = request.getParameter("targetM");
		String targetD = request.getParameter("targetD");
		
		
		Schedule schedule = new Schedule();
		schedule.setScheduleNo(scheduleNo);
		schedule.setScheduleMemo(scheduleMemo);
		
		//Dao 호출
		ScheduleDao scheduleDao = new ScheduleDao();
		int row = scheduleDao.updateSchedule(schedule);
		
		//요청 성공 디버깅
		if(row == 1) {
			System.out.println("일정 수정성공");
			
		}else {
			System.out.println("일정 수정실패");
		}
		
		//리다이렉트 
		response.sendRedirect(request.getContextPath()+"/schedule/scheduleByDay?targetY="+targetY+"&targetM="+targetM+"&targetD="+targetD);
	}

}
