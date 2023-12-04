<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>댓글 수정</h1>
	<p>수정하려면 비밀번호를 입력하세요.</p>
	<form method="post" action="${pageContext.request.contextPath}/comment/modifyComment">
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
				<textarea rows="3" cols="80">${map.noticeContent }
					</textarea>
				</td>
			</tr>
		</table>
		<h3>수정할 댓글</h3>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" value="${map.memberId}" name="memberId" readonly="readonly"></td>
			</tr>
			<tr>
				<th>댓글</th>
				<td><textarea rows="3" cols="80" name="commentContent">${map.commentContent }
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
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>