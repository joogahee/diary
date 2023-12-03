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
	<h1>일정 수정</h1>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/schedule/modifySchedule">
			<input type="hidden" name="scheduleNo" value="${schedule.scheduleNo }">
			<input type="hidden" name="targetY" value="${targetY }">
			<input type="hidden" name="targetM" value="${targetM }">
			<input type="hidden" name="targetD" value="${targetD }">
			<table border="1">
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="3" cols="80" name="scheduleMemo" >${schedule.scheduleMemo}</textarea>
					</td>
				</tr>	
			</table>
				<button type="submit">수정</button>
		</form>
	</div>
</body>
</html>