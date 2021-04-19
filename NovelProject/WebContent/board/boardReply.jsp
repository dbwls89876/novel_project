<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.BoardBean" %>
<%
	BoardBean article = (BoardBean) request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<script language="javascript">
</script>
<style type="text/css">
#registForm {
	width: 1500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
	color: #606E5E;
}

table {
	margin: auto;
	width: 1500px;
	text-align: center;
}

.td_left {
	width: 120px;
	text-align: right;
	font: bold 15px "맑은 고딕", arial;
	color: #606E5E;
	padding: 5px 20px;
	hight: 30px;
}

.td_right {
	width: 300px;
	hight: 30px;
}

#commandCell {
	text-align: center;
	padding: 20px;
}

</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<section id="writeForm">
		<form action="boardReplyPro.bo" method="post" name="boardform">
		<input type="hidden" name="page" value="<%=nowPage %>"/>
		<input type="hidden" name="boardID" value="<%=article.getBoardID() %>">
		<input type="hidden" name="replyID" value="<%=article.getReplyID() %>">
		<input type="hidden" name="replyLV" value="<%=article.getReplyLV() %>">
		<input type="hidden" name="replyStep" value="<%=article.getReplyStep() %>">
		
			<table>
				<tr>
					<td colspan="2"><h3>게시판글등록</h3></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">제목</label></td>
					<td class="td_right"><input type="text"
						placeholder="제목을 입력해 주세요." name="title" id="title"
						required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="memberID">작성자</label></td>
					<td class="td_right"><%=session.getAttribute("memberID")%></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea placeholder="내용을 입력해 주세요." id="content"
							name="content" cols="40" rows="15" required="required"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="button" value="목록" onClick="location.href='boardList.bo'">&nbsp;&nbsp;
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" />
			</section>
		</form>
	</section>
</body>
</html>