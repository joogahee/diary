package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dao.NoticeDao;
import vo.Member;
import vo.Notice;

@WebServlet("/notice/noticeOne")
public class noticeOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		//요청
		NoticeDao noticeDao = new NoticeDao();
		Notice notice = new Notice();
		notice = noticeDao.selectNoticeOne(noticeNo);
		
		// 로그인한 아이디의 레벨 확인
		MemberDao memberDao = new MemberDao();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		int memberLevel = memberDao.levelMember(loginMember.getMemberId());
		System.out.println("로그인한 member의 level은 " + memberLevel + "입니다");
		
		//jsp로 보내기
		request.setAttribute("notice", notice);
		request.setAttribute("memberLevel", memberLevel);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeOne.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
