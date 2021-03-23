<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.BoardBean" %>
<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<style type = "text/css">
#articleForm {
	width : 500px;
	height : 500px;
	border : 1px solid red;
	margin : auto;
}

h2 {
	text-align : center;
}

#basicInfoArea {
	height : 40px;
	text-align : center;
}

#articleContentArea {
	background: #606E5E;
	color: white;
	margin-top : 20px;
	height : 350px;
	text-align : center;
	overflow  :auto;
}

#commandList {
	margin : auto;
	width : 500px;
	text-align : center;
}
</style>
</head>
<body>
<section id = "articleForm">
	<section id="basicInfoArea">
		<h2>글 제목 : 
		<%=article.getTitle() %></h2>
		<p>작성자 : <%=article.getMemberID() %></p>
		<p>작성일 : <%=article.getDate() %> | 조회수 : <%=article.getReadCount() %></p>
	</section>
	<section id="articleContentArea">
		내용 : 
		<%=article.getContent() %>
	</section>
</section>
<section id = "commandList">
	<a href="boardModifyForm.bo?boardID=<%=article.getBoardID() %>&page=<%=nowPage%>"> [수정] </a>
	<a href="boardDeleteForm.bo?boardID=<%=article.getBoardID() %>&page=<%=nowPage%>"> [삭제] </a>
	<a href="boardList.bo?page=<%=nowPage%>"> [목록] </a>
	&nbsp;&nbsp;

</section>
</body>
</html>