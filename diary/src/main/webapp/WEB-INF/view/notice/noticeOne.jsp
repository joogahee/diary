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
		<h1>공지사항 상세보기</h1>
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${notice.noticeNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${notice.noticeTitle}</td>
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
				<th>작성일</th>
				<td>${notice.createdate}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/notice/removeNotice?noticeNo=${notice.noticeNo}">
			삭제
		</a>
		<a href="${pageContext.request.contextPath}/notice/modifyNotice?noticeNo=${notice.noticeNo}">
			수정
		</a>
	</div>
</body>
</html>