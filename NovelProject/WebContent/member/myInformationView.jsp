<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	button{
		width:280px;
		height:60px;
		color:white;
		background-color: #82937F;
		border:0;
	}
	.blockCenter{
		position: absolute;
		top:50%; left:50%;
		width:1000px; height:500px;
		margin-left: -200px;
		margin-top: -200px;
		text-align: center;
		font-size:25px;
		color: #82937F;
	}
</style>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<div class="container my-4">
	<h1 class="display-4 text-success">회원정보</h1>
</div>
<hr class="greenLine">

<section id = "memberInfoArea">
<div class="blockCenter">
	<div class="row my-3">
		<div class="col-md-3">
			<label for="memberID">아이디 </label>		
		</div>
		<div class="col-md-3">
			${member.memberID }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="password">비밀번호 </label>	
		</div>
		<div class="col-md-3">
			${member.password }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="name">이름 </label>	
		</div>
		<div class="col-md-3">
			${member.name }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="nickname">닉네임 </label>	
		</div>
		<div class="col-md-3">
			${member.nickname }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="mobile">휴대전화 </label>	
		</div>
		<div class="col-md-3">
			${member.mobile }
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="postCode">주소 </label>	
		</div>
		<div class="col-md-3">
			${member.postCode }
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="roadAddress"> </label>
		</div>
		<div class="col-md-3">
			${member.roadAddress }
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="detailAddress"></label>
		</div>
		<div class="col-md-3">
			${member.detailAddress }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="money">캐쉬 </label>
		</div>
		<div class="col-md-3">
			${member.money }
		</div>
	</div>
	<div class="row my-5">
		<button onclick="location.href='myInformationModifyForm.me'">수정</button>&nbsp;&nbsp;
		<button onclick="location.href='myInformationDelete.me'">탈퇴</button>
	</div>
</div>
</section>
</body>
</html>