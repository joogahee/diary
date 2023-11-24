<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/schedule.css">-->
</head>
<body>

	<h2>안녕하세요! ${loginMember.memberId} 님</h2>

	<div>
		<p><a class="button" href="${pageContext.request.contextPath}/member/logoutMember">로그아웃</a>
		<!-- LogoutMemberController.doGet() --[session invalidate]-- /login/Member-->
		<a class="button" href="${pageContext.request.contextPath}/member/modifyPwMember">비밀번호수정</a>
		<!-- 수정폼 ModifyMemberController.doGet() -- modifyMemberPw.jsp /member/loginMember-->
		<!-- 수정액션 ModifyMemberController.doPost() [session invalidate]-- modifyMember.jsp /member/loginMember -->
		<a class="button" href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a></p>
		<!-- 탈퇴폼 RemoveMemberController.doGet() -- removeMember.jsp /member/loginMember-->
		<!-- 탈퇴액션 RemoveMemberController.doPost() [session invalidate]-- removeMember.jsp /member/loginMember -->
	</div>
	
	<!-- 공지 -->
	<div>
		<h3>공지사항</h3>
		<a href="${pageContext.request.contextPath}/notice/addNotice">공지추가</a>
		<div>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="n" items="${list2}">
					<tr>
						<td>${n.noticeNo}</td>
						<td>
							<a href="${pageContext.request.contextPath}/notice/noticeOne?noticeNo=${n.noticeNo}">
								${n.noticeTitle}
							</a>
						</td>
						<td>${n.createdate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<!-- 달력 -->
	<div class="container">
		<h1>${targetY }년 ${targetM+1}월 </h1>
		
		<div>
			<a class="button2" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
			<a class="button2" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM+1}">다음달</a>		
		</div>
	</div>
	
		<br>
		
	<div>
		<div>
			<table border="1">
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
				<tr>
					<c:forEach var="i" begin="1" end="${totalTd}" step="1">
						<c:set var="d" value="${i - beginBlank}"></c:set>
						<td>
							<c:if test="${ d < 1 || d > lastD }">
								&nbsp;
							</c:if>
							<c:if test="${!(d < 1 || d > lastD )}">
								<a href="${pageContext.request.contextPath}/schedule/scheduleByDay?targetY=${targetY}&targetM=${targetM+1}&targetD=${d}">
									${d}
								</a>
								<div>
									<c:forEach var="m" items="${list}">
										<c:if test="${m.scheduleDay == (d)}">
											<div>${m.cnt}</div>
											<div>${m.memo}</div>
										</c:if>
									</c:forEach>
								</div>
									
							</c:if>
							
							<c:if test="${i<totalTd && i%7==0 }">
								</tr><tr>
							</c:if>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>