<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="fontawesome-i2svg-active fontawesome-i2svg-complete">
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

<link href="css/styles.css" rel="stylesheet" />
	
<title>Insert title here</title>
</head>
<body id="page-top">
	<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
		<div class="container">
			<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath }/index.dir">NOVEL_PROJECT</a>
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto my-2 my-lg-0">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"  href="#">작품</a>
						<ul>
							<li><a href="${pageContext.request.contextPath }/newLiteraryList.lit">신작</a></li>
							<li><a href="${pageContext.request.contextPath }/totalLiteraryList.lit">연재작</a></li>
						</ul>
					</li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/totalFunding.fun">펀딩 둘러보기</a>
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
								<a class="nav-link js-scroll-trigger" href="loginForm.dir">로그인</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath }/logoutForm.dir">로그아웃</a>
							</c:otherwise>
						</c:choose>
					</li>
					<c:if test="${memberID eq 'admin' }">
					<li class="nav-item"><a class="nav-link js-scroll-trigger" href="adminPage.dir">관리자 페이지</a></li>
					</c:if>
					
					<c:if test="${memberID != null}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger" href="#">마이페이지</a>
						<ul>
							<li><a href="${pageContext.request.contextPath }/myInformationView.me">내 정보</a></li>
							<li><a href="${pageContext.request.contextPath }/myLiteraryList.lit">내 작품</a></li>
						</ul>
					</li>
					</c:if>
					<c:if test="${memberID != null}">
						<li class="nav-item"><a class="nav-link js-scroll-trigger" href="cashCharge.dir">충전</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>