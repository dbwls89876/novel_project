<%@ page import="vo.Edition" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#listForm{
		width: 640px;
		border: 1px solid red;
		margin: auto;
	}

</style>
</head>
<body>
<section id="listForm">
	${edition.title }<br>
	${edition.content }<br>
</section>
</body>
</html>