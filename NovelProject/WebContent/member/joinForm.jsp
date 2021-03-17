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
<script>
function idCheck(){
	window.open("idCheckForm.jsp","","width=300,height=200");
}
</script>
</head>
<body>
<form name="joinform" action="joinForm.me" method="post">
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
<table border=1>
	<tr>
		<td colspan="2" class="td_title">
		회원가입
		</td>
	</tr>
	<tr>
		<td><label for="memberID">ID : </label></td>
		<td align=left><input type="text" name="memberID" id="memberID" required/>
		<input type="button" value="ID 중복 확인" onclick="idCheck()" /></td>
	</tr>
	<tr>
		<td><label for="password">비밀번호 : </label></td>
		<td align=left><input type="password" name="password" id="password"/></td>
	</tr>
	<tr>
		<td><label for="passwordchk">비밀번호 확인 : </label></td>
		<td align=left><input type="password" name="passwordchk" id="passwordchk" /></td>
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
		<td align=left><input type="text" name="mobile" id="mobile"/>
		<input type="button" value="Email 중복확인" onclick="window.open('emailCheck.jsp?openInit=ture','','width=300,height=200')" /></td>
	</tr>
	<tr>
		<td><label for="zipcode">우편번호 : </label></td>
		<td align=left><input type="text" name="zipcode" id="zipcode"/>
		<input type="button" value="검색" onclick="window.open('zipcode.jsp?openInit=ture','','width=300,height=200')"></td>
	</tr>
	<tr>
		<td><label for="address">주소 : </label></td>
		<td align=left><input type="text" name="address" id="address"/></td>
	</tr>
	<tr>
		<td><label for="address1">상세 주소 : </label></td>
		<td align=left><input type="text" name="address1" id="address1" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="가입"/>
			<input type="reset" value="다시작성"/>
</table>

</form>
</body>
</html>