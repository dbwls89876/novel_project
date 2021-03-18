<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.BoardBean"%>
<%
	BoardBean article = (BoardBean) request.getAttribute("article");
String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC게시판</title>
<script language="javascript"></script>
<style type="text/css">
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

table {
	margin: auto;
	width: 450px;
}

td h3 {
	text-align: left;
}

.td_left {
	width: 150px;
	text-align: center;
}

.td_right {
	width: 300px;
}

#commandCell {
	text-align: center;
	padding: 30px;
}

#title {
	height: 25px;
	width: 295px;
}
</style>
</head>
<body>
	<section id="writeForm">
		<form action="boardReplyPro.bo" method="post" name="boardform">
			<input type="hidden" name="page" value="<%=nowPage%>" /> <input
				type="hidden" name="boardId" value="<%=article.getBoardID()%>">
			<input type="hidden" name="ref" value="<%=article.getRef()%>">
			<input type="hidden" name="lev" value="<%=article.getLev()%>">
			<input type="hidden" name="seq" value="<%=article.getSeq()%>">
			<table>
				<tr>
					<td colspan="2"><h3>게시판글등록</h3></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">제목</label></td>
					<td class="td_right"><input type="text" name="title"
						id="title" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="id">작성자</label></td>
					<td class="td_right"><input type="text" name="id" id="id" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변등록" />&nbsp;&nbsp; <input
					type="submit" value="다시작성" />
			</section>
		</form>
	</section>
</body>
</html>