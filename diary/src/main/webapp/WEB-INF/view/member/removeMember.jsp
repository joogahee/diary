<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	<div class="login-container">
		<h1>회원탈퇴</h1>
		<p>탈퇴하려면 비밀번호를 다시 입력하세요.</p>
			<form id="deleteForm" method="post" action="${pageContext.request.contextPath}/member/removeMember">
				<div class="login-form">
					<input type="text" id="memberId" name="memberId" value="${loginMember.memberId }" readonly="readonly" class="login-input">
					<input type="password" id="memberPw" placeholder="비밀번호를 입력하세요." name="memberPw" class="login-input">
				</div>
				<button id="deleteBtn" class="registration-button">탈퇴</button>
			</form>
	</div>
</body>

<script type="text/javascript">
	$('#deleteBtn').click(function() {
		if($('#memberPw').val().length < 1) {
			alert('비밀번호를 입력하세요');
		}else {
			$('#deleteForm').submit();
		}
	});
</script>
</body>
</html>