package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;

@WebServlet("/schedule/scheduleByDay")
public class ScheduleByDayController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		int targetY = 0;
		int targetM = 0;
		int targetD = 0;
		
		//파라미터에 값이 존재한다면
		if(request.getParameter("targetY") != null && request.getParameter("targetM") != null && request.getParameter("targetD") != null) {
			System.out.println("파라미터 값 존재");
			targetY = Integer.parseInt(request.getParameter("targetY"));
			targetM = Integer.parseInt(request.getParameter("targetM"));
			targetD = Integer.parseInt(request.getParameter("targetD"));
		}
		
		//속성에 값이 존재한다면
		if(request.getAttribute("targetY") != null && request.getAttribute("targetM") != null && request.getAttribute("targetD") != null) {
			System.out.println("속성 값 존재");
			targetY = (int)request.getAttribute("targetY");
			targetM = (int)request.getAttribute("targetM");
			targetD = (int)request.getAttribute("targetD");
		}
		
		System.out.println(targetY + "<--targetY2");
		System.out.println(targetM + "<--targetM2");
		System.out.println(targetD + "<--targetD2");
		
		//memberId 세션에서 받기
		Member member = (Member)session.getAttribute("loginMember");
		
		//일별 일정 가져오기
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Map<String, Object>> list = scheduleDao.selectScheduleByDay(member.getMemberId(), targetY, targetM, targetD);
		
		//list 디버깅
		System.out.println(list.size()+" <--일 별 리스트 사이즈");
		
		//scheduleByDay 모델 request 속성으로 넘겨주기
		request.setAttribute("list", list);
		
		//jsp에 날짜 출력을 위해 날짜값 넘겨주기/param영역안에 이미 있으므로 굳이 넘겨주지 않아도 됨
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("targetD", targetD);
		
		//포워딩 
		request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleByDay.jsp").forward(request, response);
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제나 수정 후 다시 페이지를 보여줄 때 속성값이 존재한다면 받아준다
		if(request.getAttribute("targetY") != null && request.getAttribute("targetM") != null && request.getAttribute("targetD") != null) {
			System.out.println("속성 값 존재");
			int targetY = (int)request.getAttribute("targetY");
			int targetM = (int)request.getAttribute("targetM");
			int targetD = (int)request.getAttribute("targetD");
			
			request.setAttribute("targetY", targetY);
			request.setAttribute("targetM", targetM);
			request.setAttribute("targetD", targetD);
			
			request.getRequestDispatcher("/schedule/scheduleByDay").forward(request, response);
			
		}
		
		
	}


}
