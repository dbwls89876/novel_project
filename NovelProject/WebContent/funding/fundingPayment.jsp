<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
	<tr>
		<td>
			<table border="1">
				<tr>
					<td class="novelImage" rowspan="2">
						<a href="fundingContent.fun?literaryID=${funding.literaryID }">
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
<a href="FundingPaymentUpdate.fun">후원하기</a>
</body>
</html>