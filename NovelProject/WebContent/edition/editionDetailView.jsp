<%@ page import="vo.Edition" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Edition article = (Edition)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
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
<style type="text/css">
	#literaryinfo{
		width: 1000px;
		height: 300px;
		border: 1px;
		
	}
	
</style>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>

<div id="literaryinfo">
    <div class="thumb">
		${literary.image }
	</div>
    <div class="detail">
    	<h2>${literary.title }<span class="wrt_nm">${member.nickname }</span></h2>
   	<p class="detail_info">
   	${literary.content}
   	${literary.genre }</p>
    </div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4 class="display-2">${eidition.title }</h4>
		</div>
	</div>
</div>
<hr class="grayLine">
<div class="container">
	<div class="row">
		<div class="col-md-8">
			${eidition.content }
		</div>
	</div>
</div>	
</body>
</html>