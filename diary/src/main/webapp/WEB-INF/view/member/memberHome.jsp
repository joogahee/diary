<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
th {
 	background-color: #3cb371;
}

td{
	background-color: #fffff0;
}
table {
	width: 700px;
	height: 500px;
}
p {
  	/* 수평 중앙 정렬하기 */
  	text-align: center;
}
h2{
	text-align: center;
}

.container {
 	font-family: arial;
  	font-size: 24px;
  	width: 600px;
  	height: 500px;
 	outline: 1px black;
  	margin: 0 auto;
}
.button{
	background-color: #77af9c;
    color: #d7fff1;
    margin: 9px;
    padding: 8px 18px;  
  	font-size: 14px;
  	margin-bottom: 40px;
}

.button2{
	background-color: #98fb98;
    color: #000000;
    padding: 8px 18px;  
  	font-size: 14px;
  	margin-bottom: 40px;
}


</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<!-- 달력 -->
	<div class="container">
		<h1>${targetY }년 ${targetM+1}월 </h1>
		
		<div>
			<a class="button2" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM-1}">이전달</a>
			<a class="button2" href="${pageContext.request.contextPath}/member/memberHome?targetY=${targetY}&targetM=${targetM+1}">다음달</a>		
		</div>
		<br>
		<div>
			<table>
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