<%@page import="vo.Funding"%>
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
<title>펀딩</title>
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
	img{
		overflow: hidden;
	}
</style>
</head>

<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<a href="${pageContext.request.contextPath }/moveRegister.fun">펀딩 등록하기</a>
<div class="container">
	<div class="row">
		<c:forEach var = "funding" items="${fundingList }" varStatus = "status">
			<div class="col-md-4 my-5" onclick="location.href='fundingContent.fun?literaryID=${funding.literaryID }&fundingID=${funding.fundingID}'">
				<div class="card">
					<div class="embed-responsive embed-responsive-4by3">
						<img class="card-img-top embed-responsive-item" src="images/${funding.image }" alt="fundingImage">
					</div>
					<h3 class="card-title mt-3">${funding.title}</h3>
					<fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
					<p class="card-text">${cost }% 달성</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>