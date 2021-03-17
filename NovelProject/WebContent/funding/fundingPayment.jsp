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
			<table>
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
	<tr>
</table>
</body>
</html>