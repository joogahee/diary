<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>	
	 <header>
            <h2>Login</h2>
        </header>
        
  	<div class="login">
    	<form id="loginForm" method="post" action="${pageContext.request.contextPath}/member/loginMember">
      		<div class="input-box">
      		<input type="text" placeholder="ID" id="memberId" name="memberId">
      		</div>
      		<div class="input-box">
      		<input type="password" placeholder="PASSWORD" id="memberPw" name="memberPw">
      		</div>
      		<button>로그인</button>
      		<p class="message">회원가입하기 <a id="loginBtn">로그인</a></p>
    	</form>
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
