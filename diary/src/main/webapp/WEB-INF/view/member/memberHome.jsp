<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>member Home</h1>
	<p>안녕하세요!</p>
	<p>${loginMember.memberId} 님</p>
	<p>${loginMember.memberNo} 번째 회원입니다.</p>
	<div>
		<a href="${pageContext.request.contextPath}/member/logoutMember">로그아웃</a>
		<!-- LogoutMemberController.doGet() --[session invalidate]-- /login/Member-->
		<a href="${pageContext.request.contextPath}/member/modifyPwMember">비밀번호수정</a>
		<!-- 수정폼 ModifyMemberController.doGet() -- modifyMemberPw.jsp /member/loginMember-->
		<!-- 수정액션 ModifyMemberController.doPost() [session invalidate]-- modifyMember.jsp /member/loginMember -->
		<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
		<!-- 탈퇴폼 RemoveMemberController.doGet() -- removeMember.jsp /member/loginMember-->
		<!-- 탈퇴액션 RemoveMemberController.doPost() [session invalidate]-- removeMember.jsp /member/loginMember -->
	</div>
	
	<!-- 달력 -->
	<div>
		<h1>${targetY }년 ${targetM+1}월 </h1>
		
		<div>
			<a href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
			<a href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM+1}">다음달</a>		
		</div>
		<table border="1">
			<tr>
				<c:forEach var="i" begin="1" end="${totalTd}" step="1">
					<td>
						<c:if test="${i - beginBlank < 1 || i - beginBlank > lastD }">
							&nbsp;
						</c:if>
						<c:if test="${!(i - beginBlank < 1 || i - beginBlank > lastD )}">
							<a href="">
								${i - beginBlank}
							</a>
						</c:if>
						
						<c:if test="${i<totalTd && i%7==0 }">
							</tr>
							<tr>
						</c:if>
					</td>
				</c:forEach>
				</tr>
		</table>
	</div>
	
</body>
</html>