<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/schedule.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div>
		<h1>공지사항 상세보기</h1>
		<!-- 공지사항 상세보기 폼 -->
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
					<textarea rows="3" cols="80" name="noticeContent" >${notice.noticeContent}
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
	<!-- 댓글 리스트 출력 -->
	<h1>댓글</h1>
	<div>
		<table border="1">
		<c:forEach var="c" items="${list}">
			<tr>
				<th>작성자</th>
				<td>${c.memberId}</td>
				<th>작성일</th>
				<td>${c.createdate}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="3" cols="80" name="noticeContent" >${c.commentContent}
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 로그인한 멤버의 레벨이 1 ->모든 댓글 삭제 가능 / 로그인계정과 작성자가 일치 -> 삭제 가능 -->
					<c:if test="${memberLevel == 1 || c.memberId == loginMember.memberId}">
						<a href="${pageContext.request.contextPath}/comment/removeComment?commentNo=${c.commentNo}">
							삭제
						</a>
					</c:if>
					<!--로그인계정과 작성자가 일치 -> 수정 가능  -->
					<c:if test="${c.memberId == loginMember.memberId}">
						<a href="${pageContext.request.contextPath}/comment/modifyComment?commentNo=${c.commentNo}">
							수정
						</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 댓글 작성 폼 -->
	<h1>댓글 작성</h1>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/notice/comment">
			<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
			<table border="1">
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="3" cols="80" name="commentContent"></textarea>
				</td>
			</tr>
			<tr>
				<label for="secretCheckbox">
    				<input type="checkbox" id="secretCheckbox" name="secret" value="1"> 비밀글
				</label>
			</tr>
		</table>
		<button type="submit">댓글등록</button>
		</form>
	</div>
</body>
</html>