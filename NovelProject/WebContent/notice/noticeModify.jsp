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
<title>Notice Board</title>
<script type="text/javascript">
	function modifynotice() {
		modifyform.submit();
	}
</script>
<style type="text/css">
.nTitle{
	position: absolute;
	left: 395px; top: 90px;
	font-size: 32px;
	color: #606E5E;
	font-weight: bold;
}

.nWrite a{
	color: #606E5E;
	position: absolute;
	right: 400px;
	top: 110px;
	font-size: 16px;
	font-weight: normal;
}

.nWrite a:hover{
	text-decoration: none;
	color: #606E5E;
}

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
	<div class="container">
	<div class="col-md-15">
	<section id="writeForm">
		<form action="noticeModifyPro.no" method="post"
			enctype="multipart/form-data" name="modifyform">
			<input type="hidden" name="noticeID"
				value="<%=article.getNoticeID()%>" />
			<table>
				<tr>
					<td colspan="2"><h3>공지사항수정</h3></td>
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
					<td class="td_left"><label for="file">파일 첨부</label></td>
					<td><input name="file" type="file" 
					id="file" value="<%=article.getFile() %>"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15"><%=article.getContent()%></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="button" value="수정"
					onClick="location.href='javascript:modifynotice()'">&nbsp;&nbsp;
				<input type="button" value="취소"
					onClick="location.href='javascript:history.go(-1)'">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" />
			</section>
			<input type="hidden" name="page" value="<%=nowPage%>" />
		</form>
	</section>
	</div>
	</div>
</body>
</html>