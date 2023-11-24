<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/notice/modifyNotice">
			<table border="1">
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
						<textarea rows="3" cols="80" name="noticeContent" >
							${notice.noticeContent}
						</textarea>
					</td>
				</tr>	
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
				<button type="submit">수정</button>
		</form>
	</div>

</body>
</html>