<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- Third party plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" rel="stylesheet" />
<title>Insert title here</title>
<link href="css/styles.css" rel="stylesheet" />
<!--
<style type="text/css">
body, div, ul, li {
	margin: 0;
	padding: 0;
}

ul {
	list-style: none
}

a {
	color: #606E5E;
	text-decoration: none;
}

.home {
	margin-top: 15px;
	margin-left: 10px;
	font: bold 25px "맑은 고딕", arial;
	color: #606E5E;
}

.gnb {
	width: 100%;
	height: 65px;
	margin-left: 10px;
	margin-top: 20px;
	font: bold 15px "맑은 고딕", arial;
}

.gnb>ul>li {
	display: inline-block;
	float: left;
	width: 120px;
	height: 36px;
	position: relative;
}

.gnb>ul>li>a {
	display: block;
	width: 100%;
	height: 100%;
	text-align: center;
	color: #606E5E;
} 

.gnb ul li a:hover {
	text-decoration: underline;
}

.gnb ul ul {
	display: none;
}

.gnb ul li:hover ul {
	display: block;
	width: 1290px;
	height: 36px;
	position: absolute;
	margin: 0;
	padding: 5px;
	background: #E7F0E6;
	float: left;
}

.gnb li li a {
	color: #8D8C8C;
	display: inline-block;
}

.gnb li li a:hover {
	color: #000000;
	background: none;
	text-decoration: none;
}

</style>
  -->
</head>
<body id="page-top">
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
<div class="container">
	<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath }/main.jsp">NOVEL_PROJECT</a>
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	<div class="collapse navbar-collapse" id="navbarResponsive">
		<ul class="navbar-nav ml-auto my-2 my-lg-0">
			<li class="nav-item"><a class="nav-link js-scroll-trigger"  href="#">작품</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/newLiteraryList.lit">신작</a></li>
					<li><a href="${pageContext.request.contextPath }/totalLiteraryList.lit">연재작</a></li>
				</ul>
			</li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger" href="#">펀딩 둘러보기</a>
				<ul>
					<li><a
						href="${pageContext.request.contextPath }/weekFunding.fun">
						이 주의 펀딩</a></li>
					<li><a href="${pageContext.request.contextPath }/totalFunding.fun">
						전체 펀딩</a>
					</li>
				</ul>
			</li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/noticeList.no">공지게시판 </a></li>
			<li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/boardList.bo">커뮤니티</a></li>
			<li  class="nav-item">
				<c:choose>
					<c:when test="${memberID eq null }">
						<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/member/loginForm.jsp">로그인</a>
					</c:when>
					<c:otherwise>
						<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/member/logoutForm.jsp">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</li>
			<c:if test="${memberID eq 'admin' }">
			<li class="nav-item"><a class="nav-link js-scroll-trigger"  href="admin/adminPage.jsp">관리자 페이지</a></li>
			</c:if>
			
			<c:if test="${memberID != null}">
			<li class="nav-item"><a class="nav-link js-scroll-trigger" href="#">마이페이지</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/myInformationView.me">내 정보</a></li>
					<li><a href="${pageContext.request.contextPath }/myLiteraryList.lit">내 작품</a></li>
				</ul>
			</li>
			</c:if>
		</ul>
	</div>
</div>
</nav>

</body>
</html>