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
		<table>
		<c:forEach var = "fundingGoods" items="${fundingGoodsList }" varStatus = "status">
			<tr><td>${fundingGoods.name }</td></tr>
			<tr><td>${fundingGoods.cost }원</td></tr>
			<tr><td>${fundingGoods.maxNumber - fundingGoods.count }개 남음</td></tr>
		</c:forEach>
		</table>
	</td></tr>
</table>
</body>
</html>
