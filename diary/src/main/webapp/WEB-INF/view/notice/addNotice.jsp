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
    <c:import url="/WEB-INF/view/inc/menu.jsp" />
	</div>
	<div>
	<h2 class="h1">공지사항 추가</h2>
		<form method="post" action="${pageContext.request.contextPath}/notice/addNotice" id="noticeAddForm">
			<table class="centered-table">
				<tr>
					<th>공지제목</th>
					<td><input type="text" id="noticeTitle" name="noticeTitle"></td>
				</tr>
				<tr>
					<th>공지내용</th>
					<td><textarea rows="3" cols="80" name="noticeContent" id="noticeContent"></textarea></td>
				</tr>
			</table>
		<button type="button" class="button add" id="noticeAddButton">공지입력</button>		
		</form>
	</div>
<script type="text/javascript">
	$('#noticeAddButton').click(function(){
		if($('#noticeTitle').val().length < 1){
			alert('공지 제목을 입력하세요.');
		}else if($('#noticeContent').val().length < 1) {
            alert('공지 내용을 입력하세요.'); 
        } else {
            $('#noticeAddForm').submit();
        }
	})
</script>
</body>
</html>