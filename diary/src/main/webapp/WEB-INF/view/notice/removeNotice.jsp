<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/notice/removeNotice">
			<table border="1">
				<tr>
					<th>번호</th>
					<td><input type="text" name="noticeNo" readonly="readonly" value="${noticeNo }"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
				<button type="submit">삭제</button>
		</form>
	</div>

</body>
</html>