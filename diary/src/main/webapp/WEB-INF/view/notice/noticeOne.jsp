<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/schedule.css">
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
		<!-- 로그인한 멤버의 레벨이 1일경우에만 수정, 삭제 가능 -->
		<c:if test="${memberLevel == 1}">
			<a href="${pageContext.request.contextPath}/notice/removeNotice?noticeNo=${notice.noticeNo}">
				삭제
			</a>
			<a href="${pageContext.request.contextPath}/notice/modifyNotice?noticeNo=${notice.noticeNo}">
				수정
			</a>
		</c:if>
	</div>
</body>
</html>