<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
<section id = "memberInfoArea">
<table>
	<tr>
		<td>아이디 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td>${member.password }</td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td>${member.name }</td>
	</tr>
	<tr>
		<td>닉네임 : </td>
		<td>${member.nickname }</td>
	</tr>
	<tr>
		<td>휴대폰 번호 : </td>
		<td>${member.mobile }</td>
	</tr>
	<tr>
		<td>주소 : </td>
		<td>${member.address }</td>
	</tr>
	<tr>
		<td colspan=2>
			<a href="myInformationModifyForm.me">수정</a>
			<a href="myInformationDelete.me">탈퇴</a>
		</td>
	</tr>	
</table>
</section>
</body>
</html>