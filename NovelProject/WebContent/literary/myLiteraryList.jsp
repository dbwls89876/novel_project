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
<title>Insert title here</title>
<style type="text/css">
#listForm {
	width: 700px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center
}

table {
	margin: auto;
	width: 550px;
}

.div_empty {
	background-color: red;
	width: 100% height: 100%;
	text-align: center;
}

#todayImageList {
	text-align: center;
}

#productImage {
	width: 150px;
	height: 150px;
	border: none;
}

#todayImage {
	width: 100px;
	height: 100px;
	border: none;
}
</style>
</head>
<body>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
		<a href="${pageContext.request.contextPath }/literaryRegistForm.lit">작품 등록하기</a>
<table>
	<tr>	
		<c:forEach var="literary" items="${literaryList }" varStatus="status">
		<td>	
			<a href="editionWriterView.ed?id=${literary.id}">
			<img src="images/${literary.image}" class="literaryImage" /></a><br>
			${literary.title}<br> ${literary.genre}<br>
		</td>
		</c:forEach>
	</tr>
</table>
</body>
</html>