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
#articleForm {
	width: 500px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

#basicInfoArea {
	height: 40px;
	text-align: center;
}

#articleContentArea {
	background: #606E5E;
	color: white;
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
}
</style>
</head>
<body>
	<table border="0">
		<tr>
			<td align="center"><br> <jsp:include page="../menuTop.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<section id="articleForm">
		<section id="basicInfoArea">
			<h2>
				<%=article.getTitle()%></h2>
			<p>
				작성자 :
				<%=article.getMemberID()%></p>
			<p>
				작성일 :
				<%=article.getDate()%>
				| 조회수 :
				<%=article.getReadCount()%></p>
		</section>
		<section id="articleContentArea">
	
			<img src="images/<%=article.getFile()%>"> <br>
			<%=article.getContent()%>
			
		</section>
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
