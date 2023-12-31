<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	<div class="login-container">
	<h1 class="login-title">회원가입</h1>
		<form id="addForm" method="post" action="${pageContext.request.contextPath}/member/addMember">
			<div class="login-form">
					<input type="text" id="memberId" name="memberId" placeholder="아이디를 입력하세요." class="login-input">
					<input type="password" id="memberPw" name="memberPw" placeholder="비밀번호를 입력하세요." class="login-input" >
					<input type="password" id="memberPw2" placeholder="비밀번호를 다시 입력하세요." class="login-input">
			<button id="addBtn" class="login-button" type="button">회원가입</button>
				<br>
			<a href="${pageContext.request.contextPath}/member/loginMember" class="a-link">로그인하기</a>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
        $('#addBtn').click(function() {
            if ($('#memberId').val().length < 1) {
                alert('아이디를 입력하세요.');
            } else if ($('#memberPw').val().length < 1) {
                alert('비밀번호를 입력하세요.');
            } else if ($('#memberPw').val() != $('#memberPw2').val()) {
                alert('비밀번호확인이 다릅니다.');
            } else {
                $('#addForm').submit();
            }
        });
</script>

</html>
