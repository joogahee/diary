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
		
		//Dao 요청
		notice = noticeDao.selectNoticeOne(noticeNo);
		
		//requet에 notice 저장 -> 폼에서 원래의 notice값 출력을 위해
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
		
		//매개변수 notice 설정
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		
		//session에서 로그인된 member 
		Member member = (Member)session.getAttribute("loginMember");
		
		//session에서 로그인된 memberId 
		String memberId = member.getMemberId();
		
		//로그인된 memberId를 notice객체에 세팅
		notice.setMemberId(memberId);
		
		//Dao요청 
		NoticeDao noticeDao = new NoticeDao();
		int row = noticeDao.updateNotice(notice, password);
		
		//수정 성공 디버깅
		if(row == 1) {
			System.out.println("notice update 성공");
		}else {
			System.out.println("notice update 실패");
		}
		
		//리다이렉트
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
	}

}
