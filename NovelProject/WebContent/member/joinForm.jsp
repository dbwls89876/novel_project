<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 회원가입 페이지</title>
<style>
	table{
	margin:auto;
	width:600px;
	border:1px solid gray;
	text-align:center;
	}
	.td_title{
	font-weight:bold;
	font-size:x-large;
	}
</style>

</head>
<body>
<form name="joinform" action="joinForm.me" method="post">
<table border=1>
	<tr>
		<td colspan="2" class="td_title">
		회원가입 페이지
		</td>
	</tr>
	<tr>
		<td><label for="memberID">아이디 : </label></td>
		<td align=left><input type="text" name="memberID" id="memberID" required/>
			
		</td>
	</tr>
	<tr>
		<td><label for="password">비밀번호 : </label></td>
		<td align=left><input type="password" name="password" id="password"/></td>
	</tr>
	
	<tr>
		<td><label for="name">이름 : </label></td>
		<td align=left><input type="text" name="name" id="name"/></td>
	</tr>
	<tr>
		<td><label for="nickname">닉네임 : </label></td>
		<td align=left><input type="text" name="nickname" id="nickname"/></td>
	</tr>
	<tr>
		<td><label for="mobile">휴대폰번호 : </label></td>
		<td align=left><input type="text" name="mobile" id="mobile"/></td>
	</tr>
	
	<tr>
		<td><label for="address">주소 : </label></td>
		<td align=left><input type="text" name="address" id="address"/></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="가입"/>
			<input type="reset" value="다시작성"/>
</table>
</form>
</body>
</html>