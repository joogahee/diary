<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	
	<h1 class="h1">일정 수정</h1>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/schedule/modifySchedule">
			<input type="hidden" name="scheduleNo" value="${schedule.scheduleNo }">
			<input type="hidden" name="targetY" value="${targetY }">
			<input type="hidden" name="targetM" value="${targetM }">
			<input type="hidden" name="targetD" value="${targetD }">
			<table class="centered-table">
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="3" cols="80" name="scheduleMemo" >${schedule.scheduleMemo}</textarea>
					</td>
				</tr>	
			</table>
				<button type="submit" class="button modify">수정</button>
		</form>
	</div>
</body>
</html>