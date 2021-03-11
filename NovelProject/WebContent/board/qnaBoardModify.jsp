<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardBean" %>
<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage=(String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC게시판</title>
	<script type="text/javascript">
	function modifyboard() {
			modifyform.submit();
	}	
	</script>
	<style type="text/css">
	#registForm {
		width : 500px;
		height : 600px;
		border : 1px solid red;
		margin : auto;
	}
	
	h2 {
		text-align : center;
	}
	
	table {
		margin : auto;
		width : 450px;
	}
	
	.td_left {
		width : 150px;
		background : orange;
	}
	
	.td_right {
		width : 300px;
		background : skyblue;
	}
	
	#commandCell {
		text-align : center;
	}

	</style>
</head>
<body>
<section id = "writeForm">
<h2>게시판글수정</h2>
	<form action = "boardModifyPro.bo" method = "post" name = "modifyform">
	<input type = "hidden" name = "boardID" value = "<%=article.getBoardID()%>"/>
	<table>
		<tr>
			<td class = "td_left">
				<label for = "title">제목</label>
			</td>
			<td class = "td_right">
				<input type = "text" name = "title" id = "title" value = "<%=article.getTitle() %>"/>
			</td>
		</tr>
		<tr>
			<td class = "td_left">
				<label for = "id">글쓴이</label>
			</td>
			<td class = "td_right">
				<input type = "text" name = "id" id = "id" value = "<%=article.getId() %>"/>
			</td>
		</tr>
		<tr>
			<td class = "td_left">
				<label for = "content">내용</label>
			</td>
			<td>
				<textarea id = "content" name = "content" cols="40" rows="15"><%=article.getContent() %></textarea>
			</td>
		</tr>
	</table>
	<section id = "commandCell">
		<a href = "javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
		<a href = "javascript:history.go(-1)">[뒤로]</a>
	</section>
	<input type="hidden" name = "page" value = "<%=nowPage %>"/>
	</form>
</section>
</body>
</html>