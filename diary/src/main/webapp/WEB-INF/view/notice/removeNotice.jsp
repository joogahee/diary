<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp" />
	</div>
	<div>
		<h2 class="h1">공지사항 삭제</h2>
		<form method="post" action="${pageContext.request.contextPath}/notice/removeNotice" id="noticeDeleteForm">
			<table class="centered-table">
				<tr>
					<th>번호</th>
					<td><input type="text" name="noticeNo" readonly="readonly" value="${noticeNo }"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password" id="password"></td>
				</tr>
			</table>
				<button class="button delete" type="button" id="noticeDeleteButton">삭제</button>
		</form>
	</div>

</body>
<script type="text/javascript">
	$('#noticeDeleteForm').click(function() {
		if($('#password').val().length < 1) {
			alert('비밀번호를 입력하세요');
		}else {
			$('#noticeDeleteForm').submit();
		}
	});
</script>
</html>