<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body class="container">
	<div class="menu">
		<!-- 로고 -->
		<div class="logo">
            <span class="logo-text">다이어리</span>
        </div>
	
		<!-- 메뉴 -->
	    <ul>
	        <li><a href="${pageContext.request.contextPath}/member/memberHome">홈으로</a></li>
	        <li><a href="${pageContext.request.contextPath}/member/logoutMember">로그아웃</a></li>
			<!-- LogoutMemberController.doGet() --[session invalidate]-- /login/Member-->
	        <li><a href="${pageContext.request.contextPath}/member/modifyPwMember">비밀번호수정</a></li>
			<!-- 수정폼 ModifyMemberController.doGet() -- modifyMemberPw.jsp /member/loginMember-->
			<!-- 수정액션 ModifyMemberController.doPost() [session invalidate]-- modifyMember.jsp /member/loginMember -->
	        <li><a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a></li>
			<!-- 탈퇴폼 RemoveMemberController.doGet() -- removeMember.jsp /member/loginMember-->
			<!-- 탈퇴액션 RemoveMemberController.doPost() [session invalidate]-- removeMember.jsp /member/loginMember -->
	    </ul>
	    
	</div>
</body>
</html>