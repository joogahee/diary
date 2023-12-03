<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>댓글 삭제</h1>
	<p>삭제하려면 비밀번호를 입력하세요.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/removeComment">
		<input type="hidden" value="${map.commentNo }" name="commentNo">
		<input type="hidden" value="${map.noticeNo }" name="noticeNo">
		<table border="1">
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
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" value="${map.memberId}" name="memberId"></td>
			</tr>
			<tr>
				<th>댓글</th>
				<td><textarea rows="3" cols="80" name="noticeContent">${map.commentContent }
					</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${map.createdate }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<button type="submit">삭제</button>
	</form>
</body>
</html>