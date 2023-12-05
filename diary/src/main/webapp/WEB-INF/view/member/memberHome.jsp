<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/diary.css">
</head>
<body>
	<!-- menu -->
	<div id="menu">
    <c:import url="/WEB-INF/view/inc/menu.jsp" />
	</div>
		<h2>${loginMember.memberId}님 환영합니다.</h2>
		
	<div>
	<p class="right-align">
	<c:if test="${not empty applicationScope.currentCnt}">
    	현재 접속자 수: <c:out value="${applicationScope.currentCnt}" /> 명/
	</c:if>

	<c:if test="${not empty applicationScope.sumByToday}">
   	 	총 접속자 수: <c:out value="${applicationScope.sumByToday}" /> 명/
	</c:if>
	
	<c:if test="${not empty applicationScope.todayCnt}">
   	 	오늘 총 접속자 수: <c:out value="${applicationScope.todayCnt}" /> 명
	</c:if>
	</p>
	</div>
	
	<!-- 공지 -->
	<div>
		<h3>공지사항</h3>
		<div>
			<table class="centered-table">
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
			<!-- 로그인한 멤버의 레벨이 1일경우에만 공지 추가 가능 -->
			<c:if test="${memberLevel == 1}">
				<a class="button add" href="${pageContext.request.contextPath}/notice/addNotice">공지추가</a>
			</c:if>
			</table>
		</div>
	</div>
	
	<!-- 달력 -->
	<div class="container">
		<h1 class="h1">
			<a class="button one" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
				${targetY }년 ${targetM+1}월 
			<a class="button one" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM+1}">다음달</a>	
		</h1>
		
		<div>
			
				
		</div>
	</div>
	
		<br>
		
	<div>
		<div>
			<table class="calendar-table">
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
						<c:set var="d" value="${i - beginBlank}">
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
											<div>
												<c:if test="${m.cnt} > 0"></c:if>
													${m.cnt}
											</div>
											<div>${m.memo}</div>
										</c:if>
									</c:forEach>
								</div>
									
							</c:if>
							<c:if test="${i<totalTd && i%7==0 }">
								</tr><tr>
							</c:if>
						</td>
				</c:set>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>