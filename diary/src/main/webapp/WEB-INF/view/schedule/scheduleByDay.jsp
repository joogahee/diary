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
	
	<div>
		<div>
			<!-- param영역에도 날짜가 저장되어 있으므로 -->
			<!--<h2>${loginMember.memberId}님의 ${param.targetY}년 ${param.targetM+1}월 ${param.targetD}일의 일정</h2>-->
			<!-- 로 받아도 됨 --> 
			<h2 class="h1">${loginMember.memberId}님의 ${targetY}년 ${targetM}월 ${targetD}일의 일정</h2> 
		</div>
		<div>
			<table class="centered-table">
				<tr>
					<th>번호</th>
					<th>일정</th>
					<th>작성일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="s" items="${list}">
					<tr>
						<td>${s.scheduleNo}</td>
						<td>${s.memo}</td>
						<td>${s.createdate}</td>
						<td>
							<form method="get" action="${pageContext.request.contextPath}/schedule/modifySchedule">
								<input type="hidden" name="targetY" value="${targetY}">
								<input type="hidden" name="targetM" value="${targetM}">
								<input type="hidden" name="targetD" value="${targetD}">
								<input type="hidden" name="scheduleNo" value="${s.scheduleNo }">
								<button type="submit" class="button modify">수정</button>
							</form>
						</td>
						<td>
							<form method="post" action="${pageContext.request.contextPath}/schedule/removeScheduleByDay">
								<input type="hidden" name="targetY" value="${targetY}">
								<input type="hidden" name="targetM" value="${targetM}">
								<input type="hidden" name="targetD" value="${targetD}">
								<input type="hidden" name="scheduleNo" value="${s.scheduleNo }">
								<button type="submit" class="button delete">삭제</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 추가 폼 -->
		<div>
		<h3>일정추가</h3>
			<table class="centered-table">
				<form method="post" action="${pageContext.request.contextPath}/schedule/addSchedule" id="scheduleAddForm">
					<input type="hidden" name="targetY" value="${targetY}">
					<input type="hidden" name="targetM" value="${targetM}">
					<input type="hidden" name="targetD" value="${targetD}">
				<tr>
					<td>
						<textarea rows="3" cols="80" name="scheduleMemo" id="scheduleMemo"></textarea>
					</td>
					<td>
						<button type="button" id="scheduleAddButton" class="button add">추가</button>
					</td>
				</tr>
			</form>
			</table>
		</div>		
	</div>
</body>
<script type="text/javascript">
	$('#scheduleAddButton').click(function() {
		if($('#scheduleMemo').val().length < 1) {
			alert('일정 내용을 입력하세요.');
		}else {
			$('#scheduleAddForm').submit();
		}
	});
</script>
</html>