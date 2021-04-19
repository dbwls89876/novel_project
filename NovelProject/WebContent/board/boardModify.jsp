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
h3 {
	text-align: center;
}

table {
	margin: auto;
	width: 1500px;
}

.td_left {
	width: 150px;
}

.td_right {
	width: 300px;
}

#commandCell {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<section id="writeForm">
		<form action="boardModifyPro.bo" method="post" name="modifyform">
			<input type="hidden" name="boardID" value="<%=article.getBoardID()%>" />
			<table>
				<tr>
					<td colspan="2"><h3>게시판글수정</h3></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">제목</label></td>
					<td class="td_right"><input type="text" name="title"
						id="title" value="<%=article.getTitle()%>" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="memberID">작성자</label></td>
					<td class="td_right"><%=session.getAttribute("memberID")%></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15"><%=article.getContent()%></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp; <a
					href="javascript:history.go(-1)">[뒤로]</a>
			</section>
			<input type="hidden" name="page" value="<%=nowPage%>" />
		</form>
	</section>
</body>
</html>