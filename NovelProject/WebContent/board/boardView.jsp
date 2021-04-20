<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	BoardBean article = (BoardBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board View</title>
<style type="text/css">
table {
	margin:65px auto;
	width: 1110px;
	text-align: center;
	font-size: 16px;
	margin-bottom: 0;
}
#tr_top {
	background: #606E5E;
	text-align: center;
	color: white;
	font-size: 16px;
	font-weight: bold;
}
#tr_info {
	text-align: right;
	font-size: 14px;
}
#articlePictureArea img {
	width: 900px;
	margin: 30px 0;
}
#articleContentArea {
	margin: 30px;
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
				<h2 class="display-5 font-weight-bold" style="color:#606E5E;">커뮤니티</h2>
			</div>
		</div>
		<div class="row">
		<div class="col-md-10">
	<section id="articleForm">
		<table>
			<tr id="tr_top" height="45px">
				<td>
					<%=article.getTitle()%>
				</td>
			</tr>
			<tr id="tr_info" height="60px">
				<td><%=article.getMemberID()%>  |  <%=article.getDate()%>  |  조회수 <%=article.getReadCount()%></td>
			</tr>
			<tr id="articlePictureArea">
				<td><%if(!(article.getFile()==null)) { %>
				<img src="images/<%=article.getFile() %>"><%} %></td>
			</tr>
			<tr id="articleContentArea">
				<td><%=article.getContent()%></td>
			</tr>
		</table>
	</section>
	<section id="commandCell">
	
		<input type="button" value="수정"
					onClick="location.href='boardModifyForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>'">&nbsp;&nbsp;
		<input type="button" value="삭제"
					onClick="location.href='boardDeleteForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>'">&nbsp;&nbsp;
		<input type="button" value="답글"
					onClick="location.href='boardReplyForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>'">&nbsp;&nbsp;
		<input type="button" value="목록"
					onClick="location.href='boardList.bo?page=<%=nowPage%>'">

	</section>
	</div>
	</div>
	</div>
	
</body>
</html>

