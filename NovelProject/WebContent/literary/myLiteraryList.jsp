<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.Literary"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	table{
		margin: auto;
		width: 1000px;
		border:1px solid;
	}

</style>
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr>
		<td>
			<a href="${pageContext.request.contextPath }/literaryRegistForm.lit">작품 등록하기</a>
		</td>
	</tr>
	<tr>	
		<c:forEach var="literary" items="${myLiteraryList }" varStatus="status">
		<td>	
			<a href="editionWriterList.ed?id=${literary.id}">
			<img src="images/${literary.image}" class="literaryImage" /></a><br>
			${literary.title}<br> ${literary.genre}<br>
		</td>
		</c:forEach>
	</tr>
</table>
</body>
</html>