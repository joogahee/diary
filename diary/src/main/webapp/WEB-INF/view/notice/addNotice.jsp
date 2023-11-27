<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/schedule.css">
</head>
<body>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/notice/addNotice">
			<table border="1">
				<tr>
					<td>공지제목</td>
					<td><input type="text" id="noticeTitle" name="noticeTitle"></td>
				</tr>
				<tr>
					<td>공지내용</td>
					<td><textarea rows="3" cols="80" name="noticeContent"></textarea></td>
				</tr>
			</table>
		<button type="submit">공지입력</button>		
		</form>
	</div>

</body>
</html>