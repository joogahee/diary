package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import vo.Comment;
import vo.Member;

@WebServlet("/notice/comment")
public class AddCommentController extends HttpServlet {
	
	//comment 추가 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
			}
		
		//요청분석
		String commentContent = request.getParameter("commentContent");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String secretParam = request.getParameter("secret");

		// "true" 문자열이면 true, null이면 false로 처리
		boolean secret = "true".equalsIgnoreCase(secretParam);
		
		//요청 디버깅
		System.out.println(commentContent + " <--commentContent " + noticeNo + " <--noticeNo " + secret + " <--secret ");
		
		//session에서 로그인된 memberId 
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		
		//매개변수 comment 설정
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		comment.setSecret(secret);
		comment.setMemberId(memberId);
		comment.setNoticeNo(noticeNo);
		
		//매개변수 디버깅
		System.out.println(comment);
		
		//Dao 요청
		CommentDao commentDao = new CommentDao();
		int row = commentDao.insertComment(comment);
		
		//댓글입력 성공 디버깅
		if(row == 1) {
			System.out.println("댓글입력 성공");
		}else {
			System.out.println("댓글입력 실패");
		}
		
		//리다이렉트
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo);
	}

}
