<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
	<tr>
		<td><a href="${pageContext.request.contextPath }/moveRegister.fun">펀딩 등록하기</a></td>
	</tr>
	<tr>
		<td>
			<table>
			<c:forEach var = "funding" items="${fundingList }" varStatus = "status">
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
			</c:forEach>
			</table>
		</td>
	</tr>
</table>

</body>
</html>