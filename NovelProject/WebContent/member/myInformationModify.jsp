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
		<td><label for="id">아이디 : </label></td>
		<td align=left><input type="hidden" name="id" value='${member.memberID }'/> </td>
	</tr>
	<tr>
		<td><label for="password">비밀번호 : </label></td>
		<td align=left><input type="password" name="password" id="password" value='${member.password }' /></td>
	</tr>
	<tr>
		<td><label for="name">이름 : </label></td>
		<td align=left><input type="text" name="name" id="name" value='${member.name }' /></td>
	</tr>
		<tr>
		<td><label for="nickname">닉네임 : </label></td>
		<td align=left><input type="text" name="nickname" id="nickname" value='${member.nickname }' /></td>
	</tr>
	<tr>
		<td><label for="mobile">휴대폰번호 : </label></td>
		<td align=left><input type="text" name="mobile" id="mobile" value='${member.mobile }' /></td>
	</tr>
	<tr>
		<td><label for="address">주소 : </label></td>
		<td align=left><input type="text" name="address" id="address" value='${member.address }' /></td>
	</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="수정완료"/>&nbsp;&nbsp;
		<input type="reset" value="수정취소"/>
		<input type="button" value="회원탈되" id="Delete" />
	</td>
</tr>


	<tr>
		<td>
			<a href="myInformationModifyAction.me?id=${member.memberID }">수정</a>
		</td>
		<td>
			<a href="myInformationDeleteAction.me?id=${member.memberID }">탈퇴</a>
		</td>
	</tr>	
</table>
</section>
</body>
</html>