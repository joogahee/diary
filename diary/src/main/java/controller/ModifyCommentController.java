package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;


@WebServlet("/comment/modifyComment")
public class ModifyCommentController extends HttpServlet { 
	
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}		
		
		//요청분석
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		
		//Dao 요청
		CommentDao commentDao = new CommentDao();
		Map<String,Object> map = new HashMap<>();
		map = commentDao.selectCommentOne(commentNo);
		
		// secret 값이 null이면 0으로 설정
		if (map.get("secret") == null) {
		    map.put("secret", 0);
		}
		
		//modifyComment 출력
		request.setAttribute("map", map);
		
		//포워딩
		request.getRequestDispatcher("/WEB-INF/view/comment/modifyComment.jsp").forward(request, response);
	}

	//액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//필터 인코딩 버그
		request.setCharacterEncoding("utf-8");
		
		//요청분석
		String password = request.getParameter("password");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String commentContent = request.getParameter("commentContent");
		String memberId = request.getParameter("memberId");
		String paramSecret = request.getParameter("secret");
		int secret = 0;
		
		//secret이 null이 아니면 1 
		if(paramSecret != null) {
			secret = Integer.parseInt(paramSecret);
		}
		
		//Dao 매개변수 세팅
		Map<String,Object> map = new HashMap<>();
		map.put("password", password);
		map.put("noticeNo", noticeNo);
		map.put("commentNo", commentNo);
		map.put("commentContent", commentContent);
		map.put("memberId", memberId);
		map.put("secret", secret);
		
		//Dao 호출
		CommentDao commentDao = new CommentDao();
		int row = commentDao.updateComment(map);
		
		//수정 성공 디버깅
		if(row == 1) {
			System.out.println("comment update 성공");
		}else {
			System.out.println("comment update 실패");
		}		
		
		//리다이렉트
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeNo="+noticeNo);		

	}

}
