package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeDao;
import vo.Member;

@WebServlet("/notice/removeNotice")
public class removeNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		request.setAttribute("noticeNo", noticeNo);
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/notice/removeNotice.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}	
		
		//요청분석
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String password = request.getParameter("password");
		
		//요청값 디버깅
		System.out.println("삭제할 공지: " + noticeNo + "password: " + password);
		
		//요청
		NoticeDao noticeDao = new NoticeDao();
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		int row = noticeDao.deleteNotice(noticeNo, password, memberId);
		
		//요청 디버깅
		if(row == 1) {
			System.out.println(noticeNo + "번 공지삭제 성공");
			
		}else {
			System.out.println(noticeNo + "번 공지삭제 실패");
		}
		
		//리다이렉트 
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
		
	}

}
