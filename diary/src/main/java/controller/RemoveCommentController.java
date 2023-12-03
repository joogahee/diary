package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import dao.MemberDao;
import vo.Member;


@WebServlet("/comment/removeComment")
public class RemoveCommentController extends HttpServlet {
	
	//comment 삭제 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		//요청분석
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		//Dao요청
		CommentDao commentDao = new CommentDao();
		Map<String,Object> map = commentDao.selectCommentOne(commentNo);
		
		//CommentOne 출력
		request.setAttribute("map", map);
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/comment/removeComment.jsp").forward(request, response);
	}

	//comment 삭제 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청분석
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String password = request.getParameter("password");
		
		//로그인한 계정
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		MemberDao memberDao = new MemberDao();
		
		//로그인한 계정의 Level
		int memberLevel = memberDao.levelMember(member.getMemberId());
		
		//Dao 요청
		CommentDao commentDao = new CommentDao();
		int row = commentDao.deleteComment(commentNo, password, memberId, memberLevel);
		
		//요청 성공 디버깅
		if(row == 1) {
			System.out.println("댓글 삭제 성공");
			
		}else {
			System.out.println("댓글 삭제 실패");
		}
		
		//리다이렉트 
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo);
	}

}
