<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>	
	<div class="login-page">
  		<div class="form">
  			<!--  
    		<form class="register-form">
      			<input type="text" placeholder="ID"/>
      			<input type="password" placeholder="PASSWORD"/>
      			<button>회원가입</button>
      			<p class="message">로그인하기 <a href="#">회원가입</a></p>
    		</form>
    		-->
    		<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
      			<input type="text" placeholder="ID" id="memberId" name="memberId">
      			<input type="password" placeholder="PASSWORD" id="memberPw" name="memberPw">
      			<button>로그인</button>
      			<p class="message">회원가입하기 <a id="loginBtn">로그인</a></p>
    		</form>
  		</div>
	</div>
</body>


<script type="text/javascript">
    $(document).ready(function() {
        $('#loginBtn').click(function() {
            if($('#memberId').val().length < 1) {
                alert('아이디를 입력하세요');
            } else if($('#memberPw').val().length < 1) {
                alert('비밀번호를 입력하세요'); 
            } else {
                $('#loginForm').submit();
            }
        });
    });
</script>

</html>
