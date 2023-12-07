<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp"/>
	</div>
	
	<div>
		<h2 class="h1">공지사항</h2>
		<!-- 공지사항 상세보기 폼 -->
		<table class="centered-table">
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
					<textarea rows="3" cols="80" name="noticeContent" readonly="readonly" >${notice.noticeContent}
					</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${notice.createdate}</td>
			</tr>
		</table>
		</div>
		<br>
		<div>
		<!-- 로그인한 멤버의 레벨이 1일경우에만 수정, 삭제 가능 -->
		<c:if test="${memberLevel == 1}">
			<a class="button delete" href="${pageContext.request.contextPath}/notice/removeNotice?noticeNo=${notice.noticeNo}">
				삭제
			</a>
			<a class="button modify" href="${pageContext.request.contextPath}/notice/modifyNotice?noticeNo=${notice.noticeNo}">
				수정
			</a>
		</c:if>
		<br>
		<br>
	</div>
	<!-- 댓글 리스트 출력 -->
	<h2 class="h1">댓글</h2>
	<div>
		<table class="centered-table">
		<c:forEach var="c" items="${list}">
			<tr>
				<th>작성자</th>
				<td>${c.memberId}</td>
				<th>작성일</th>
				<td>${c.createdate}</td>				
				<th>비밀글</th>
				<td>
					<c:if test="${c.isSecret==1}">&#x1F512;</c:if>
					<c:if test="${c.isSecret!=1}"></c:if>	
				</td>
				
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="3" cols="80" name="noticeContent" >${c.commentContent}
					</textarea>
				</td>
				<td colspan="2">
					<!-- 로그인한 멤버의 레벨이 1 ->모든 댓글 삭제 가능 / 로그인계정과 작성자가 일치 -> 삭제 가능 -->
					<c:if test="${memberLevel == 1 || c.memberId == loginMember.memberId}">
						<a class="button delete" href="${pageContext.request.contextPath}/comment/removeComment?commentNo=${c.commentNo}">
							삭제
						</a>
					</c:if>
					<!--로그인계정과 작성자가 일치 -> 수정 가능  -->
					<c:if test="${c.memberId == loginMember.memberId}">
						<a class="button modify" href="${pageContext.request.contextPath}/comment/modifyComment?commentNo=${c.commentNo}">
							수정
						</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 댓글 작성 폼 -->
	<h1 class="h1">댓글 작성</h1>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/notice/comment">
			<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
			<table class="centered-table">
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="3" cols="80" name="commentContent"></textarea>
				</td>
				<td>
					<button type="submit" class="button add">댓글등록</button>
				</td>
			</tr>
			<tr>
				<label for="secretCheckbox">
    				<input type="checkbox" id="secretCheckbox" name="secret" value="1"> 비밀글
				</label>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>