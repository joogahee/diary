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

@WebServlet("/member/loginMember")
public class LoginMemberController extends HttpServlet {
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// session 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")!=null) { 
			// 로그인된 상태
			// 리다이렉트할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
		}
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/member/loginMember.jsp").forward(request, response);
	}

	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")!=null) { 
			// 로그인된 상태
			// 리다이렉트할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
		}
		
		//요청분석
		String memberId= request.getParameter("memberId");
		String memberPw= request.getParameter("memberPw");
		
		//매개변수 paramMember 설정
		Member paramMember = new Member();	
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		//Dao요청
		MemberDao memberDao = new MemberDao();
		Member resultMember = memberDao.loginMember(paramMember);
		
		//resultMember == null -> 로그인 실패라면
		if(resultMember == null) {
			//로그인창으로 리다이렉트
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			System.out.println("로그인 실패");
			return;
		}
		
		//로그인 성공
		//세션 설정
		session.setAttribute("loginMember", resultMember);
		System.out.println(resultMember + "로그인 성공");
		
		//memberHome으로 리다이렉트
		response.sendRedirect(request.getContextPath()+"/member/memberHome");
	}

}
