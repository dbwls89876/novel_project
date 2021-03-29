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
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr><td><h1>${funding.title }</h1></td></tr>
	
	<tr><td>
		<table>
			<tr><td rowspan="6"><img src="images/${funding.image }" class="fundingImage"></td></tr>
			<tr><td>모인 금액</td></tr>
			<tr><td>${funding.nowCost }원</td></tr>
			<tr>
			<td><fmt:parseNumber var="cost" integerOnly="true" value="${funding.nowCost/funding.targetCost*100 }"/>
					${cost }% 달성
			</tr>
			<tr><td>남은 시간</td></tr>
			<tr><td>${restTime }일</td></tr>
		</table>
	</td></tr>
	
	<tr><td>
		<table> 
			<tr><td>${funding.content }</td></tr>
		</table>
	</td></tr>
	
	<tr><td>
		<c:forEach var = "fundingGoods" items="${fundingGoodsList }" varStatus = "status">
			<table border="1" onClick="location.href='fundingPayment.fun?literaryID=${funding.literaryID }&goodsID=${fundingGoods.goodsID}&cost=${fundingGoods.cost }'">
			<tr><td>${fundingGoods.name }</td></tr>
			<tr><td>${fundingGoods.cost }원</td></tr>
			<tr><td>${fundingGoods.maxNumber - fundingGoods.count }개 남음</td></tr>			
			</table>
		</c:forEach>
	</td></tr>
</table>
</body>
</html>
