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


@WebServlet("/member/addMember")
public class AddMemberController extends HttpServlet {

       
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			//로그인 되어 있는 상태
			//리다이렉트 할 컨트롤러 url
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
		}
		
		//view forward
		request.getRequestDispatcher("/WEB-INF/view/member/addMember.jsp").forward(request, response);
	}
	//액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
			}
		
		//요청분석
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println("회원가입: " + memberId + " <--memberId " + memberPw + " <--memberPw");
		
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		//매개값 디버깅
		System.out.println(paramMember.toString());

		MemberDao memberDao = new MemberDao();
		int row = memberDao.insertMember(paramMember);
		
		if(row == 1) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
		
		response.sendRedirect(request.getContextPath()+"/member/loginMember");
	}

}
