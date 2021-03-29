<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>이달의 펀딩</title>
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
		border:1px solid;
	}
	.novelImage{
		width: 150px;
		height: 150px;
		border: none;
	}
</style>
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr>
		<td><a href="${pageContext.request.contextPath }/moveRegister.fun">펀딩 등록하기</a></td>
	</tr>
	<tr>
		<td>
			<table>
			<c:forEach var = "funding" items="${weekFundingList }" varStatus = "status">
				<tr>
					<td class="novelImage" rowspan="2">
						<a href="fundingContent.fun?literaryID=${funding.literaryID }&fundingID=${funding.fundingID}">
							<img src="images/${funding.image }" class="novelImage"/>				
						</a>
					</td>
					<td>${funding.title}</td>
				</tr>
				<tr>
					<td><fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
					${cost }% 달성
					</td>
				</tr>
			</c:forEach>
			</table>
		</td>
	</tr>
</table>
</body>
</html>