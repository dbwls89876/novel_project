<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BoardBean" %>
<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Board Reply</title>
<script language="javascript"></script>
<style type="text/css">
#registForm {
	width : 500px;
	height : 610px;
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
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
<section id = "writeForm">
<h2>게시판글등록</h2>
	<form action = "noticeReplyPro.bo" method = "post" name = "boardform">
	<input type = "hidden" name = "page" value = "<%=nowPage%>"/>
	<input type = "hidden" name = "noticeID" value = "<%=article.getNoticeID()%>">
	<input type = "hidden" name = "ref" value = "<%=article.getRef()%>">
	<input type = "hidden" name = "lev" value = "<%=article.getLev()%>">
	<input type = "hidden" name = "seq" value = "<%=article.getSeq()%>">
	<table>
		<tr>
			<td class = "td_left">
				<label for = "title">제목</label>
			</td>
			<td class = "td_right">
				<input type = "text" name = "title" id = "title"/>
			</td>
		</tr>
		<tr>
			<td class = "td_left">
				<label for = "id">글쓴이</label>
			</td>
			<td class = "td_right">
				<input type = "text" name = "id" id = "id"/>
			</td>
		</tr>		
		<tr>
			<td class = "td_left">
				<label for = "content">내용</label>
			</td>
			<td>
				<textarea id = "content" name = "content" cols="40" rows="15"></textarea>
			</td>
		</tr>
	</table>
	<section id = "commandCell">
		<input type="submit" value="답변등록"/>&nbsp;&nbsp;
		<input type="submit" value="다시작성"/>
	</section>
	</form>
</section>
</body>
</html>