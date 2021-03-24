<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<body>
	<div class="home">
		<a href="main.jsp">NOVEL_PROJECT</a>
	</div>
	<div class="gnb">
		<ul>
			<li><a href="#">작품</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/newLiteraryList.lit">신작</a></li>
					<li><a href="${pageContext.request.contextPath }/totalLiteraryList.lit">연재작</a></li>
				</ul>
			</li>
			<li><a href="#">펀딩 둘러보기</a>
				<ul>
					<li><a
						href="${pageContext.request.contextPath }/weekFunding.fun">
						이 주의 펀딩</a></li>
					<li><a href="${pageContext.request.contextPath }/totalFunding.fun">
						전체 펀딩</a>
					</li>
				</ul>
			</li>
			<li><a href="${pageContext.request.contextPath }/noticeList.no">공지게시판 </a></li>
			<li><a href="${pageContext.request.contextPath }/boardList.bo">커뮤니티</a></li>
			<li>
				<c:choose>
					<c:when test="${memberID eq null }">
						<a href="member/loginForm.jsp">로그인</a>
					</c:when>
					<c:otherwise>
						<a href="member/logoutForm.jsp">로그아웃</a>
					</c:otherwise>
				</c:choose>
			</li>
			<c:if test="${memberID eq 'admin' }">
			<li>
			<a href="admin/adminPage.jsp">관리자 페이지</a>
			</li>
			</c:if>
			
			<c:if test="${memberID eq 'admin' }">
			<li><a href="#">마이페이지</a>
				<ul>
					<li><a href="${pageContext.request.contextPath }/myInformationModify.me">내 정보 수정</a></li>
					<li><a href="${pageContext.request.contextPath }/myLiteraryList.lit">내 작품</a></li>
				</ul>
			</li>
			</c:if>
		</ul>
	</div>
</body>
</html>