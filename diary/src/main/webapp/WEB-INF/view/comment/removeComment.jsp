<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	
	<h1 class="h1">댓글 삭제</h1>
	<p>삭제하려면 비밀번호를 입력하세요.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/removeComment" id="commentRemoveForm">
		<input type="hidden" value="${map.commentNo }" name="commentNo">
		<input type="hidden" value="${map.noticeNo }" name="noticeNo">
		<table class="centered-table">
			<tr>
				<th>공지 제목</th>
				<td>${map.noticeTitle }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
				<textarea rows="3" cols="80" name="noticeContent">${map.noticeContent }
					</textarea>
				</td>
			</tr>
		</table>
		<h3>삭제할 댓글</h3>
		<table class="centered-table">
			<tr>
				<th>작성자</th>
				<td><input type="text" value="${map.memberId}" name="memberId"></td>
			</tr>
			<tr>
				<th>댓글</th>
				<td><textarea rows="3" cols="80" name="commentContent">${map.commentContent }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${map.createdate }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" id="password"></td>
			</tr>
		</table>
		<button type="button" id="commentRemoveButton" class="button delete">삭제</button>
	</form>
</body>
<script type="text/javascript">
	$('#commentRemoveButton').click(function() {
		 if($('#password').val().length < 1) {
            alert('비밀번호를 입력하세요.'); 
        }else {
			$('#commentRemoveForm').submit();
		}
	});
</script>
</html>