<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 로그인 페이지</title>
<style>
	table{
	margin:auto;
	width:400px;
	border:1px solid gray;
	text-align:center;
	}
	.td_title{
	font-weight:bold;
	font-size:x-large;
	}
	input{
		width:500px;
		height:50px;
		align:center;
	}
	button{
		width:500px;
		height:50px;
		color:white;
		background-color: #606E5E;
		border:0;
	}
</style>

</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<form name="loginform" action="${pageContext.request.contextPath }/loginForm.me" method="post">
<div class="container my-4">
	<h1 class="display-4 text-success">로그인</h1>
</div>
<hr class="greenLine">
<div class="container my-5">
	<div class="row my-2">
		<div class="col-md-offset-3 col-md-6"><input type="text" name="memberID" id="memberID" placeholder="아이디 입력"/></div>
	</div>
	<div class="row my-2">
		<div class="col-md-offset-3 col-md-6"><input type="text" name="password" id="password" placeholder="비밀번호 입력"/></div>
	</div>
	<div class="row my-2">
		<div class="col-md-offset-3 col-md-6"><button onclick="location.href='javascript:loginform.submit()'">로그인</button></div>
	</div>
	<div class="row my-2">
		<hr class="greenLine my-5">
		<a href="joinForm.dir">회원가입</a>
	</div>
</div>

</form>
</body>
</html>