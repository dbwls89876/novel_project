<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	img{
		height:150px;
		overflow: hidden;
	}
</style>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="menuTop.jsp"></jsp:include>
</div>
<div class="container grayBox my-5">
	<p class="text-right"><a href="${pageContext.request.contextPath }/totalLiteraryList.lit">연재작 보러가기</a></p>
	<div class="row">
		<div class="m-1 ml-3"><h3>전체 작품</h3></div>
	</div>
	<div class="row">
		<c:forEach var="literary" items="${literaryList }" varStatus = "status">
			<div class="col-md-2" style="width:300px;">	
				<div class="card">
					<div class="embed-responsive-16by9 my-2">
						<img class="card-img-top embed-responsive-item" src="images/${literary.image }" alt="literaryImage">										
					</div>
					<h3 class="card-title my-2">${literary.title }</h3>
					<p class="card-text my-2">${literary.genre }</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="container grayBox">
	<p class="text-right"><a href="${pageContext.request.contextPath }/totalFunding.fun">펀딩 보러가기</a></p>
	<div class="row">
			<div class="m-1 ml-3"><h3>최신 펀딩</h3></div>
		</div>
		<div class="row">
			<c:forEach var="funding" items="${fundingList }" varStatus = "status">
				<div class="col-md-2" style="width:300px;">	
					<div class="card">
						<div class="embed-responsive-16by9 my-2" >
							<img class="card-img-top embed-responsive-item" src="fundingImages/${funding.image }" alt="fundingImage">										
						</div>
						<h5 class="card-title my-2">${funding.title }</h5>
					</div>
				</div>
			</c:forEach>
		</div>
</div>
<div class="container">
	
	<div class="row">
		<div class="col-6 m-6">
			<div class="row grayBox">
				<div class="container">
					<p class="text-right"><a href="${pageContext.request.contextPath }/noticeList.no">공지 보러가기</a></p>
					<div class="row mt-2">
						<div ><h3>공지게시판</h3></div>
					</div>
				</div>	
				
				<div class="row">
				</div>
			</div>
		</div>
		<div class="col-6 m-6">
			<div class="row grayBox">
				<p class="text-right"><a href="${pageContext.request.contextPath }/boardList.bo">커뮤니티 보러가기</a></p>				<div class="row">
				<div class="m-1 ml-3"><h3>공지게시판</h3></div>
				</div>
				<div class="row">
				</div>
				
			</div>
		</div>
	</div>
</div>
</body>
</html>