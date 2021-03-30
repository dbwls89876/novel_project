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
#articleForm{
	width:500px;
	height:500px;
	border:1px solid red;
	margin: auto;
}

#basicInfoArea{
	height: 40px;
	text-align: center;
}

#articleContentArea{
	background: orange;
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}
</style>
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<!-- 게시판 수정 -->
	<section id="articleForm">
		<section id="basicInfoArea">
		<%=article.getTitle() %>
		</section>
		<section id="articleContentArea">
			<%=article.getContent() %>
		</section>
	</section>
	<section id="commandList">
		<a href="editionModifyForm.ed?num=<%=article.getNum() %>&page=<%=nowPage %>"> [수정] </a>&nbsp;
		<a href="editionDeleteForm.ed?num=<%=article.getNum() %>&page=<%=nowPage %>">[삭제] </a>
		<a href="editionReaderList.ed?page=<%=nowPage %>">[목록]</a>
		&nbsp;&nbsp;
	</section>
</body>
</html>