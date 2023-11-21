package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;

@WebServlet("/member/modifyPwMember")
public class ModifyMemberController extends HttpServlet {
	
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
				
		//view forward
		request.getRequestDispatcher("/WEB-INF/view/member/modifyPwMember.jsp").forward(request, response);
	}
	
	//액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String newMemberPw = request.getParameter("newMemberPw");
		
		//매개값 디버깅
		System.out.println("비밀번호 수정: " + memberId + " <--memberId " + memberPw + " <--memberPw");
		System.out.println("새 비밀번호: " + newMemberPw + " <--newMemberPw");
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.updateMember(memberId, memberPw, newMemberPw);
		
		//요청 성공 디버깅
		if(row == 1) {
			System.out.println("비밀번호 수정 성공");
		}else {
			System.out.println("비밀번호 수정 실패");
		}
		
		//session 삭제
		session.invalidate();
				
		//회원 탈퇴 후 로그인창으로 리다이렉트 
		response.sendRedirect(request.getContextPath()+"/member/loginMember");
		
	}

}
