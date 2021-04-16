<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.Literary"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>

</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<div class="col-md-4 my-5">
<a href="${pageContext.request.contextPath }/literaryRegistForm.lit?id=${member.id}">작품 등록하기</a></div>
<div class="container">
	<div class="row">
		<c:forEach var = "literary" items="${myLiteraryList }" varStatus = "status">
			<div class="col-md-4 my-5" onclick="location.href='editionWriterList.ed?literaryID=${literary.literaryID}'">
				<div class="card">
					<div class="embed-responsive embed-responsive-4by3">
						<img class="card-img-top embed-responsive-item" src="images/${literary.image }" alt="literaryImage">
					</div>
					<h6 class="card-title mt-3 p-2">${literary.title}</h6>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>
