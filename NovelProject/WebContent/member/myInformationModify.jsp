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
<form name="modifyform" action="myInformationModifyPro.me" method="post">
<table>
	<tr>
		<td>아이디 : </td>
		<td>${member.memberID }</td>
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
		<input type="submit" value="수정완료">
			
			<input type="reset" value="수정취소"/>
		</td>
	</tr>	
</table>
</form>
</body>
</html>