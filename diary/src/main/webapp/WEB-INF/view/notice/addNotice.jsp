<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <c:import url="/WEB-INF/view/inc/menu.jsp" />
	</div>
	<div>
	<h1 class="h1">공지사항 추가</h1>
		<form method="post" action="${pageContext.request.contextPath}/notice/addNotice">
			<table class="centered-table">
				<tr>
					<th>공지제목</th>
					<td><input type="text" id="noticeTitle" name="noticeTitle"></td>
				</tr>
				<tr>
					<th>공지내용</th>
					<td><textarea rows="3" cols="80" name="noticeContent"></textarea></td>
				</tr>
			</table>
		<button type="submit" class="button add">공지입력</button>		
		</form>
	</div>

</body>
</html>