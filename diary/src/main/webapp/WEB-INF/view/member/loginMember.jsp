<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">

</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	
  	<div class="login-container">
		<h1 class="login-title">로그인</h1>
    	<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
      		<div class="login-form">
      		<input class="login-input" type="text" placeholder="아이디를 입력하세요." id="memberId" name="memberId">
      		</div>
      		<div class="login-form">
      		<input class="login-input" type="password" placeholder="비밀번호를 입력하세요." id="memberPw" name="memberPw">
      		</div>
      		<button class="login-button">로그인</button>
      		<a href="${pageContext.request.contextPath}/member/addMember" class="registration-button">회원가입</a>
    	</form>
  	</div>
</body>


<script type="text/javascript">
    $(document).ready(function() {
        $('#loginBtn').click(function() {
            if($('#memberId').val().length < 1) {
                alert('아이디를 입력하세요');
            } else if($('#memberPw').val().length < 1) {
                alert('비밀번호를 입력하세요'); 
            } else {
                $('#loginForm').submit();
            }
        });
    });
</script>

</html>
