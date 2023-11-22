package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		
		//view forward
		request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
		
	}

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
		System.out.println("회원탈퇴: " + memberId + " <--memberId " + memberPw + " <--memberPw");

		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		//매개값 디버깅
		System.out.println(paramMember.toString());

		MemberDao memberDao = new MemberDao();
		int row = memberDao.deleteMember(paramMember);
		
		//요청 성공 디버깅
		if(row == 1) {
			System.out.println(memberId + "회원탈퇴 성공");
			
		}else {
			System.out.println(memberId + "회원탈퇴 실패");
		}
		
		//session 삭제
		session.invalidate();
		
		//회원 탈퇴 후 로그인창으로 리다이렉트 
		response.sendRedirect(request.getContextPath()+"/member/loginMember");
	}

}
