<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section id = "memberInfoArea">
<table>
	<tr>
		<td>아이디 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>닉네임 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>휴대폰 번호 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>주소 : </td>
		<td>${member.memberID }</td>
	</tr>
	<tr>
		<td>
			<a href="myInformationModifyAction.me?id=${member.memberID }">수정</a>
		</td>
		<td>
			<a href="myInformationDeleteAction.me?id=${member.memberID }">수정</a>
		</td>
	</tr>	
</table>
</section>
</body>
</html>