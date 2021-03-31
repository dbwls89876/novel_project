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

<title>${funding.title }</title>
<style type="text/css">
img{
	overflow:hidden;
	max-width: 100%;
	height: 500px;
	margin-right: 40px;
}
</style>
</head>
<fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h1 class="display-2">${funding.title }</h1>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div class="my-5"><img src="images/${funding.image }"></div>
		</div>
		<div class="col-md-4">
			<div class="mt-5">모인금액</div>
			<div class="my-2"><h1>${funding.nowCost }원</h1></div>
			<div class="my-3">${cost }% 달성</div>
			<div class="mt-5">남은 시간</div>
			<div class="my-2"><h1>${restTime }일</h1></div>
		</div>
	</div>
</div>
<hr class="grayLine">
<div class="container">
	<div class="row">
		<div class="col-md-8">
			${funding.content }
		</div>
		<div class="col-md-4">
			<c:forEach var = "fundingGoods" items="${fundingGoodsList }" varStatus = "status">
				<div class="grayBox">
					<table onClick="location.href='fundingPayment.fun?literaryID=${funding.literaryID }&goodsID=${fundingGoods.goodsID}&cost=${fundingGoods.cost }'">
					<tr><td>${fundingGoods.name }</td></tr>
					<tr><td>${fundingGoods.cost }원</td></tr>
					<tr><td>${fundingGoods.maxNumber - fundingGoods.count }개 남음</td></tr>			
					</table>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

</body>
</html>
