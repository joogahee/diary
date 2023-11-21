<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
	Member member = new Member();
	member = (Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 변경</h1>
	<form id="modifyForm" method="post" action="<%=request.getContextPath()%>/member/modifyPwMember">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="memberId" name="memberId" value="<%=member.getMemberId() %>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>기존 비밀번호</td>
				<td><input type="password" id="memberPw" name="memberPw"></td>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" id="newMemberPw" name="newMemberPw"></td>
			</tr>
			<tr>
				<td>새 비밀번호 확인</td>
				<td><input type="password" id="newMemberPw2" name="newMemberPw2"></td>
			</tr>
		</table>
		<button id="modifyBtn">변경</button>
	</form>

</body>
<script type="text/javascript">
	$('#modifyBtn').click(function(){
		if($('#memberPw').val().length < 1){
			alert('기존 비밀번호를 입력하세요');
		}else if($('#memberModifyPw').val().length < 1){
			alert('새 비밀번호를 입력하세요');
		}else if($('#memberModifyPw2').val().length < 1){
			alert('새 비밀번호확인을 입력하세요');
		}else if($('#memberModifyPw').val() != $('#memberModifyPw2').val()){
			alert('새 비밀번호를 다시 입력하세요');
		}else {
			$('#modifyForm').submit();
		}
			
		
	})
</script>
</html>