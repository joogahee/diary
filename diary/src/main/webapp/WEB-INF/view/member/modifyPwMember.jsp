<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<h1>비밀번호 변경</h1>
		<p>비밀번호를 다시 입력하세요.</p>
		<form id="modifyForm" method="post" action="${pageContext.request.contextPath}/member/modifyPwMember">
			<div class="login-form">
				<input type="text" id="memberId" name="memberId" value="${loginMember.memberId}" readonly="readonly" class="login-input">
				<input type="password" id="memberPw" name="memberPw" class="login-input" placeholder="비밀번호를 입력하세요.">
				<input type="password" id="newMemberPw" name="newMemberPw" class="login-input" placeholder="새 비밀번호를 입력하세요.">
				<input type="password" id="newMemberPw2" name="newMemberPw2" class="login-input" placeholder="새 비밀번호를 다시 입력하세요.">
				<button id="modifyBtn" type="button" class="registration-button">변경</button>
			</div>
		</form>
 	</div>
</body>
<script type="text/javascript">
	$('#modifyBtn').click(function(){
		if($('#memberPw').val().length < 1){
			alert('기존 비밀번호를 입력하세요');
			return;
		}else if($('#newMemberPw').val().length < 1){
			alert('새 비밀번호를 입력하세요');
			return;
		}else if($('#newMemberPw2').val().length < 1){
			alert('새 비밀번호확인을 입력하세요');
			return;
		}else if($('#newMemberPw').val() != $('#newMemberPw2').val()){
			alert('새 비밀번호를 다시 입력하세요');
			return;
		}else {
			$('#modifyForm').submit();
		}
			
		
	})
</script>
</html>