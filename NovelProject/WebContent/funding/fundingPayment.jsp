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
<title>펀딩 결제창</title>
<style type="text/css">
.grayBox{
	border:1px solid gray;
	border-radius: 15px;
	padding: 10px;
	margin-top: 10px;
	margin-bottom: 30px;
}
table th{
	font-weight: bold;
	padding:5px;
	padding-right: 30px;
}
</style>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-4"><img src="images/${funding.image }" class="novelImage"/></div>
		<div class="col-md-8">
			<h1 class="my-2">${funding.title}</h1>
			<fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
			<h4 class="my-2">${cost }% 달성</h4>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div class="mt-3">책 정보</div>
			<div class="grayBox">
				<table>
				<tr><th>구성</th><td>${fundingGoods.name }</td></tr>
				<tr><th>금액</th><td>${fundingGoods.cost }</td></tr>
				<tr><th>예상 전달일</th><td>${funding.deliveryDate }</td></tr>
				</table>
			</div>
			<div class="my-2">후원자 정보</div>
			<div class="grayBox">
				<table>
					<tr><th>연락처</th><td><input type="text" value="${member.mobile }"></td></tr>
					<tr><th>배송지</th><td>${member.address }</td></tr>	
				</table>
			</div>
		</div>
		<div class="col-md-4">
			<div class="mt-5">
			<div class="grayBox">
			후원하기는 아직 실현되지 않은 창작자의 프로젝트에 제작비를 후원하는 과정입니다. 그렇기 때문에 제작 계획이 변경될 수 있으며, 프로젝트를 완수하고 후원자와 성실히 소통할 책임은 프로젝트 주체인 창작자에게 있습니다.
			</div>
			</div>
			<button type="button" class="btn btn-light" onclick = "location.href ='fundingPaymentUpdate.fun?fundingID=${funding.fundingID }&goodsID=${fundingGoods.goodsID}&cost=${fundingGoods.cost}'">후원하기</button>
		</div>
	</div>
</div>
</body>
</html>