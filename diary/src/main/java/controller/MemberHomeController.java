package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/memberHome")
public class MemberHomeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		/*
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		*/
		
		//달력에 출력하는데 필요한 모델
		//1) 출력하고자 하는 년/월/1일
		Calendar firstD = Calendar.getInstance();
		firstD.set(Calendar.DATE, 1);	//2023년 11월 1일
		
		int targetY = firstD.get(Calendar.YEAR);
		int targetM = firstD.get(Calendar.MONTH);
		
		if(request.getParameter("targetY") != null && request.getParameter("targetM") != null) {
			firstD.set(Calendar.YEAR, Integer.parseInt(request.getParameter("targetY")));
			firstD.set(Calendar.MONTH,Integer.parseInt(request.getParameter("targetM")));
			//2023년 12가 되면 2024년 1 로 바뀜 / 2023년 0월이 들어오면 자동으로 2022년에 12월로 바뀐다
			targetY = firstD.get(Calendar.YEAR);
			targetM = firstD.get(Calendar.MONTH);
		}
		
		//2) firstD를 통해 마지막 일자( ex)30,31,...)
		int lastD = firstD.getActualMaximum(Calendar.DATE);
		
		//3) firstD를 통해 1일의 요일 -> 시작 공백
		int beginBlank = firstD.get(Calendar.DAY_OF_WEEK) - 1;	//요일 맵핑 숫자값 - 1
		
		//4) 전체 TD가 7로 나누어 떨어지도록 endBlank 설정
		int endBlank = 0;
		if((beginBlank + lastD) % 7 != 0) {
			endBlank = 7 - (beginBlank + lastD) % 7;
		}
		
		//5) 전체 TD의 개수
		int totalTd = beginBlank + endBlank + lastD;
		
		// 넘기는데 2가지 방법 1.하나하나 보내는 것 , 2.map사용해서 랩핑해서 한번에 보내는 것
		
		request.setAttribute("targetY", targetY);
		request.setAttribute("targetM", targetM);
		request.setAttribute("lastD", lastD);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalTd", totalTd);
								
		request.getRequestDispatcher("/WEB-INF/view/member/memberHome.jsp").forward(request, response);
	}

}
