<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	
	<h1 class="h1">��� ����</h1>
	<p>�����Ϸ��� ��й�ȣ�� �Է��ϼ���.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/removeComment">
		<input type="hidden" value="${map.commentNo }" name="commentNo">
		<input type="hidden" value="${map.noticeNo }" name="noticeNo">
		<table class="centered-table">
			<tr>
				<th>���� ����</th>
				<td>${map.noticeTitle }</td>
			</tr>
			<tr>
				<th>����</th>
				<td>
				<textarea rows="3" cols="80" name="noticeContent">${map.noticeContent }
					</textarea>
				</td>
			</tr>
		</table>
		<h3>������ ���</h3>
		<table class="centered-table">
			<tr>
				<th>�ۼ���</th>
				<td><input type="text" value="${map.memberId}" name="memberId"></td>
			</tr>
			<tr>
				<th>���</th>
				<td><textarea rows="3" cols="80" name="commentContent">${map.commentContent }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>�ۼ���</th>
				<td>${map.createdate }</td>
			</tr>
			<tr>
				<th>��й�ȣ</th>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<button type="submit" class="button delete">����</button>
	</form>
</body>
</html>