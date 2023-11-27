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
import vo.Notice;


@WebServlet("/notice/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		Notice notice = new Notice();
		NoticeDao noticeDao = new NoticeDao();
		
		notice = noticeDao.selectNoticeOne(noticeNo);
		
		request.setAttribute("notice", notice);
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/notice/modifyNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		String password = request.getParameter("password");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		
		notice.setMemberId(memberId);
		
		NoticeDao noticeDao = new NoticeDao();
		int row = noticeDao.updateNotice(notice, password);
		
		if(row == 1) {
			System.out.println("notice update 성공");
		}else {
			System.out.println("notice update 실패");
		}
		
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
	}

}
