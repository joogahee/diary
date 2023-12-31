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
		<h2 class="h1">공지사항 수정</h2>
		<form method="post" action="${pageContext.request.contextPath}/notice/modifyNotice" id="noticeModifyForm">
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
				<button class="button modify" type="button" id="noticeModifyButton">수정</button>
		</form>
	</div>
</body>
<script type="text/javascript">
	$('#noticeModifyButton').click(function() {
		if($('#noticeTitle').val().length < 1) {
			alert('공지제목을 입력하세요');
		}else if($('#noticeContent').val().length < 1) {
            alert('공지 내용을 입력하세요.'); 
        }else {
			$('#noticeModifyForm').submit();
		}
	});
</script>
</html>