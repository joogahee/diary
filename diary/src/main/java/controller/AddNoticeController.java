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

@WebServlet("/notice/addNotice")
public class AddNoticeController extends HttpServlet {
	
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/notice/addNotice.jsp").forward(request, response);
		
	}
	
	//액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;		
		}
		
		//요청분석
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		//session에서 로그인된 memberId 
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		
		//요청디버깅
		System.out.println(noticeTitle + " 공지 제목");
		System.out.println(noticeContent + " 공지 내용");
		
		//매개변수 notice 설정
		Notice notice = new Notice();
		notice.setMemberId(memberId);
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		
		//Dao요청
		NoticeDao noticeDao = new NoticeDao();
		int row = noticeDao.insertNotice(notice);
		
		//공지입력 성공 디버깅
		if(row == 1) {
			System.out.println("공지입력 성공");
		}else {
			System.out.println("공지입력 실패");
		}
		
		//리다이렉트
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
		
	}

}
