<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.BoardBean"%>
<%
	BoardBean article = (BoardBean) request.getAttribute("article");
String nowPage = (String) request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<script type="text/javascript">
	function modifyboard() {
		modifyform.submit();
	}
</script>
<style type="text/css">
table {
	margin:65px auto;
	width: 1110px;
	font-size: 16px;
	margin-bottom: 0;
}

.td_left {
	
	text-align: center;
	color: #606E5E;
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	width: 200px;
}

.tr_right {
	text-align: left;
	left:40px;
}

hr {
	border: 1;
	width: 1110px;
	maring:auto;
	background: #606E5E;
}

#pageList {
	maring: 50px;
	text-align: center;
}

a{
	text-decoration: none;
	color: #606E5E;
}

a:hover{
	text-decoration: none;
	color: #606E5E;
}
#commandCell {
	text-align: center;
	margin: 40px;
}
</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<div class="container my-1">
	<div class="row">
			<div class="col-md-8">
				<h2 class="display-5 font-weight-bold" style="color:#606E5E;">커뮤니티 수정하기</h2>
			</div>
	</div>
	<div class="container">
	<div class="col-md-15">
	<section id="writeForm">
		<form action="boardModifyPro.bo" method="post" name="modifyform">
			<input type="hidden" name="boardID" value="<%=article.getBoardID()%>" />
			<table class="table">
				<tr>
					<td class="td_left" height="40px"><label for="title">제목</label></td>
					<td class="td_right"><input type="text" name="title"
						id="title" value="<%=article.getTitle()%>" /></td>
				</tr>
				<tr>
					<td class="td_left" height="40px"><label for="memberID">작성자</label></td>
					<td class="td_right"><%=session.getAttribute("memberID")%></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15"><%=article.getContent()%></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="button" value="수정"
					onClick="location.href='javascript:modifyboard()'">&nbsp;&nbsp;
				<input type="button" value="취소"
					onClick="location.href='javascript:history.go(-1)'">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" />
			</section>
			<input type="hidden" name="page" value="<%=nowPage%>" />
		</form>
	</section>
	</div>
	</div>
	</div>
</body>
</html>