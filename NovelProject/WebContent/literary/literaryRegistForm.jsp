<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>NovelProject_작품 등록</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="${pageContext.request.contextPath}/css/summernote/summernote-lite.js"></script>
  <script src="${pageContext.request.contextPath}/css/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/summernote/summernote-lite.css">

  <!--  -->
  
<style type="text/css">
	#registForm{
	width: 500px;
	height:610px;
	border:1px solid red;
	margin : auto;
	}
	
	h2{
		text-align: center;
	}
	
	table{
		margin: auto;
		border-collapse: separate;
  		border-spacing: 0 10px;
	}
	#commandCell{
		text-align: center;
	}
</style>
</head>
<%
session = request.getSession();
Member member = (Member)session.getAttribute("member");
%>
<body>
<div class="container mt-5">
	<div class="row">
		<button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath }/myLiteraryList.lit'">돌아가기</button>
	</div>
</div>
<form action ="literaryRegist.lit" method="post"  name="literaryform">
<div class="container h-100 grayBox">
	<div class="row m-5 p-5 justify-content-center align-self-center">
		<h1 class="dislpay-3">작품 등록</h1>
	</div>
	<div>
		<input type="hidden" name="id" id="id" value=<%=request.getParameter("id") %> required="required" />
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<label for="nickname">닉네임 : </label>&nbsp;&nbsp;
		${member.nickname }
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2"><label for="title">제목 : </label></div>
		<div class="col-md-2"><input type="text" name="title" id="title" required="required"/></div>
	</div>
	<div class="row justify-content-center align-self-center">
		<textarea class="summernote" id="summernote" name="content" placeholder="※이미지 첨부 기능x"></textarea>	
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2"><label for="title">장르</label></div>
		<input type="radio" name="genre" value="드라마" checked id="genre" /> 드라마
			<input type="radio" name="genre" value="개그" id="genre" />개그
			<input type="radio" name="genre" value="판타지" id="genre" />판타지
			<input type="radio" name="genre" value="액션" id="genre" />액션
			<input type="radio" name="genre" value="순정" id="genre" />순정
			<input type="radio" name="genre" value="스릴러" id="genre" />스릴러
			<input type="radio" name="genre" value="감성" id="genre" />감성
			<input type="radio" name="genre" value="시대극" id="genre" />시대극
			<input type="radio" name="genre" value="스포츠" id="genre" />스포츠
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2"><label for="image">이미지 첨부</label></div>
		<div class="col-md-2"><input type="file" name="image" id="image" required="required"/></div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<section id="commandCell">
			<input type="submit" value="등록">&nbsp;&nbsp;
			<input type="reset" value="다시쓰기">
		</section>
	</div>
</div>
</form>
<script>
$('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
	});
</script>
</body>
</html>