<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1 class="h1">댓글 수정</h1>
	<p>수정하려면 비밀번호를 입력하세요.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/modifyComment" id="commentModifyForm">
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
				<textarea rows="3" cols="80">${map.noticeContent }
					</textarea>
				</td>
			</tr>
		</table>
		<h3>수정할 댓글</h3>
		<table class="centered-table">
			<tr>
				<th>작성자</th>
				<td><input type="text" value="${map.memberId}" name="memberId" readonly="readonly"></td>
			</tr>
			<tr>
				<th>댓글</th>
				<td><textarea rows="3" cols="80" name="commentContent" id="commentContent">${map.commentContent }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${map.createdate}</td>
			</tr>
			<tr>
				<label for="secretCheckbox">
    				<input type="checkbox" id="secretCheckbox" name="secret" value="1"> 비밀글
				</label>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" id="password"></td>
			</tr>
		</table>
		<button type="button" id="commentModifyButton" class="button modify">수정</button>
	</form>
</body>
<script type="text/javascript">
	$('#commentModifyButton').click(function() {
		if($('#commentContent').val().length < 1) {
			alert('댓글을 입력하세요');
		}else if($('#password').val().length < 1) {
            alert('비밀번호를 입력하세요.'); 
        }else {
			$('#commentModifyForm').submit();
		}
	});
</script>
</html>