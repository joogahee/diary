<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��� ����</h1>
	<p>�����Ϸ��� ��й�ȣ�� �Է��ϼ���.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/removeComment">
		<input type="hidden" value="${map.commentNo }" name="commentNo">
		<input type="hidden" value="${map.noticeNo }" name="noticeNo">
		<table border="1">
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
		<table border="1">
			<tr>
				<th>�ۼ���</th>
				<td><input type="text" value="${map.memberId}" name="memberId"></td>
			</tr>
			<tr>
				<th>���</th>
				<td><textarea rows="3" cols="80" name="noticeContent">${map.commentContent }
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
		<button type="submit">����</button>
	</form>
</body>
</html>