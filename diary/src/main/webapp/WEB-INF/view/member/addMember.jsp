<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js">
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form id="addForm" method="post" action="<%=request.getContextPath()%>/member/addMember">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" id="memberId" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" id="memberPw" name="memberPw"></td>
			</tr>
			<tr>
				<td>memberPw확인</td>
				<td><input type="password" id="memberPw2"></td>
			</tr>
		</table>
		<button id="addBtn">회원가입</button>
	</form>
</body>

<script type="text/javascript">
	$('#addBtn').click(function() {
		if($('#memberId').val().length < 1) {
			alert('아이디를 입력하세요');
		} else if($('#memberPw').val().length < 1) {
			alert('비밀번호를 입력하세요');
		} else if($('#memberPw').val() != $('#memberPw2').val()) {
			alert('비밀번호를 확인');
		} else {
			$('#addForm').submit();
		}
	});
</script>

</html>
