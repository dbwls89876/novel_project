<%@ page import="vo.Edition" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Edition article = (Edition)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
#articleForm{
	width:500px;
	height:500px;
	border:1px solid red;
	margin: auto;
}

#basicInfoArea{
	height: 40px;
	text-align: center;
}

#articleContentArea{
	background: orange;
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}
</style>
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr>
		<td>${eidition.title }</td>
	</tr>
	<tr>
		<td>${eidition.content }</td>
	</tr>
	<tr>
		<td colspan=2>
			<a href="editionModifyForm.ed">수정</a>
			<a href="editionDeleteForm.ed">삭제</a>
			<a href="editionReaderList.ed">목록</a>
		</td>
	</tr>
</table>	
</body>
</html>