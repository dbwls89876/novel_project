<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.BoardBean"%>
<%
	BoardBean article = (BoardBean) request.getAttribute("article");
String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Board View</title>
<style type="text/css">
.nTitle{
	position: absolute;
	left: 395px; top: 90px;
	font-size: 46px;
	color: #606E5E;
	font-weight: bold;
}
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
#commandList {
	text-align: center;
	margin: 30px;
}
</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<div class="nTitle">
		공지게시판
	</div>
	
	<section id="articleForm">
		<table>
			<tr id="tr_top" height="50px">
				<td>
					<%=article.getTitle()%>
				</td>
			</tr>
			<tr id="tr_info" height="35px">
				<td>작성자 :
				<%=article.getMemberID()%></td>
			</tr>
			<tr id="tr_info" height="35px">
				<td>작성일 :
				<%=article.getDate()%>
				| 조회수 :
				<%=article.getReadCount()%></td>
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
	
	<section id="commandList">
		<a
			href="noticeModifyForm.no?noticeID=<%=article.getNoticeID()%>&page=<%=nowPage%>">
			[수정] </a> <a
			href="noticeDeleteForm.no?noticeID=<%=article.getNoticeID()%>&page=<%=nowPage%>">
			[삭제] </a> <a href="noticeList.no?page=<%=nowPage%>"> [목록] </a>
		&nbsp;&nbsp;

	</section>
</body>

