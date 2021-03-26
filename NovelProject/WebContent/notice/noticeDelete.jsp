<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int noticeID = (Integer) request.getAttribute("noticeID");
String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Board</title>
<style>
table {
	margin: auto;
	width: 1500px;
}
#passForm {
	width: 400px;
	margin: auto;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td align="center"><br> <jsp:include page="../menuTop.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<section id="passForm">
		<form name="deleteForm"
			action="noticeDeletePro.no?noticeID=<%=noticeID%>" method="post">
			<input type="hidden" name="page" value="<%=nowPage%> %>" />
			<table>
				<tr>
					<td><label>아이디 : </label></td>
					<td><input name="memberID" type="text"></td>
				</tr>
				<tr>
					<td><input type="submit" value="삭제" />&nbsp;&nbsp; <input
						type="button" value="돌아가기" onClick="javascript:history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>