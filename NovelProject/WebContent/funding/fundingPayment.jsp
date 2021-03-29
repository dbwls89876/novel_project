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
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr>
		<td>
			<table border="1">
				<tr>
					<td class="novelImage" rowspan="2">
						<img src="images/${funding.image }" class="novelImage"/>
					</td>
					<td>${funding.title}</td>
				</tr>
				<tr>
					<td><fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
					${cost }% 달성
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td>책 정보</td></tr>
	<tr>
		<td>
			<table border = "1">
				<tr><td>구성</td><td>${fundingGoods.name }</td></tr>
				<tr><td>금액</td><td>${fundingGoods.cost }</td></tr>
				<tr><td>예상 전달일</td><td>${funding.deliveryDate }</td></tr>
			</table>
		</td>
	</tr>
	
	<tr><td>후원자 정보</td></tr>
	<tr>
		<td>
			<table border = "1">
				<tr><td>연락처</td><td><input type="text" value="${member.mobile }"></td></tr>
				<tr><td>배송지</td><td>${member.address }</td></tr>	
			</table>
		</td>
	</tr>
	
</table>
<a href="fundingPaymentUpdate.fun?fundingID=${funding.fundingID }&goodsID=${fundingGoods.goodsID}&cost=${fundingGoods.cost}">후원하기</a>
</body>
</html>