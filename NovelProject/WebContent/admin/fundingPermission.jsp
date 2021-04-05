<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펀딩 신청 목록</title>
</head>
<body>
<form action="fundingPermissionUpdate.fun">
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table border="1">
	<tr>
		<td>펀딩 허가 요청 목록</td>
	</tr>
	<c:forEach var = "funding" items="${permissionWaitingList }" varStatus = "status">
	<tr>
		<td><label><input type="checkbox" name="fundingList" value="${funding.title }" onclick="location.href='fundingContent.fun?literaryID=${funding.literaryID }&fundingID=${funding.fundingID}'">
		${funding.title }</label></td>
	</tr>
	</c:forEach>
</table>
<p><input type="submit" value="허가">
</form>
</body>
</html>