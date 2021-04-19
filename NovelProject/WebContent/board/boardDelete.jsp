<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int boardID = (Integer) request.getAttribute("boardID");
String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<style>
table {
	margin: 50px auto;
	width: 1500px;
}
#passForm {
	width: 400px;
	margin: auto;
	border: 1px solid orange;
}
</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<section id="passForm">
		<form name="deleteForm"
			action="boardDeletePro.bo?boardID=<%=boardID%>" method="post">
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