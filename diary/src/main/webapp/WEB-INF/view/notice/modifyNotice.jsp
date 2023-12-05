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
		<h1 class="h1">공지사항 수정</h1>
		<form method="post" action="${pageContext.request.contextPath}/notice/modifyNotice">
			<table class="centered-table">
				<tr>
					<th>번호</th>
					<td><input type="text" name="noticeNo" readonly="readonly" value="${notice.noticeNo }"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="3" cols="80" name="noticeContent" >${notice.noticeContent}</textarea>
					</td>
				</tr>	
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
				<button class="button modify" type="submit">수정</button>
		</form>
	</div>

</body>
</html>