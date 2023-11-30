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

@WebServlet("/schedule/removeScheduleByDay")
public class RemoveScheduleByDayController extends HttpServlet {	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//디버깅
		System.out.println(request.getParameter("scheduleNo"));
		System.out.println(request.getParameter("targetY"));
		System.out.println(request.getParameter("targetM"));
		System.out.println(request.getParameter("targetD"));
		
		//요청분석
		int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));	
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		Member member = (Member)session.getAttribute("loginMember");
		
		//요청값 디버깅
		System.out.println("삭제할 scheduleNo : "+ scheduleNo + " 작성자 ID : " + member.getMemberId());
		System.out.println(targetY + "<--targetY");
		System.out.println(targetM + "<--targetM");
		System.out.println(targetD + "<--targetD");
		
		//Dao요청
		ScheduleDao scheduleDao = new ScheduleDao();
		int row = scheduleDao.deleteScheduleByDay(member.getMemberId(), scheduleNo);
		
		//요청 성공 디버깅
		if(row != 1) {
			System.out.println("일정삭제 실패");
		}else {
			System.out.println("일정삭제 성공");
		}
		
		//다시 일정페이지로 갈 수 있도록 값 넘겨주기
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		
		//일정 삭제 후 일정페이지로 리다이렉트 
		//response.sendRedirect(request.getContextPath()+"/schedule/scheduleByDay");
		response.sendRedirect(request.getContextPath()+"/member/memberHome");

	}

}
