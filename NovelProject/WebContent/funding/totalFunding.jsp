<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펀딩 둘러보기</title>
<style type="text/css">
	#listForm{
		width: 1500px;
		height: 500px;
		border: 1px solid red;
		margin: auto;
	}
	table{
		margin: auto;
		width: 1000px;
	}
	#novelImage{
		width: 150px;
		height: 150px;
		border: none;
	}
</style>
</head>
<body>
<section id="listForm">
<table>
	<tr>
		<c:forEach var = "funding" items="${fundingList }" varStatus = "status">
		<td>
			<a href="fundingContent.fun?id=${funding.id }">
				<img src="images/${funding.image }" id="novelImage"/>
		</td>
		</c:forEach>
	</tr>
</table>
</section>
</body>
</html>