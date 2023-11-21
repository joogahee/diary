<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>member Home</h1>
	안녕하세요!
	<div>
		<a href="<%=request.getContextPath()%>/member/logoutMember">로그아웃</a>
		<!-- LogoutMemberController.doGet() --[session invalidate]-- /login/Member-->
		<a href="<%=request.getContextPath()%>/member/modifyPwMember">비밀번호수정</a>
		<!-- 수정폼 ModifyMemberController.doGet() -- modifyMemberPw.jsp /member/loginMember-->
		<!-- 수정액션 ModifyMemberController.doPost() [session invalidate]-- modifyMember.jsp /member/loginMember -->
		<a href="<%=request.getContextPath()%>/member/removeMember">회원탈퇴</a>
		<!-- 탈퇴폼 RemoveMemberController.doGet() -- removeMember.jsp /member/loginMember-->
		<!-- 탈퇴액션 RemoveMemberController.doPost() [session invalidate]-- removeMember.jsp /member/loginMember -->
	</div>
</body>
</html>