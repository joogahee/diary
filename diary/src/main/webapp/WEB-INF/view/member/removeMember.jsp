<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<p>탈퇴하려면 비밀번호를 다시 입력하세요.</p>
	<form id="deleteForm" method="post" action="${pageContext.request.contextPath}/member/removeMember">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" id="memberId" name="memberId" value="${loginMember.memberId }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" id="memberPw" name="memberPw"></td>
			</tr>
			
		</table>
		<button id="deleteBtn">탈퇴</button>
	</form>
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