<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC게시판</title>
<style type="text/css">

h2 {
text-align : center;
}

table {
	margin : auto;
	width : 100%;
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
<div id="menuTop">
<table border="0">
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
</div>
<section id = "writeForm">
	<h2>게시판글등록</h2>
	<form action = "boardWritePro.bo" method = "post"
	enctype = "multipart/form-data" name = "boardform">
		<table>
			<tr>
				<td class = "td_left"><label for = "title">제목</label></td>
				<td class = "td_right"><input type="text" name="title" id="title" required="required"/></td>
			</tr>
			
			<tr>
				<td class = "td_left"><label for = "id">작성자</label></td>
				<td class = "td_right"><input type="text" name="id" id="id" required="required"/></td>
			</tr>
			
			<tr>
				<td class = "td_left"><label for = "content">내용</label></td>
				<td><textarea id="content" name="content" cols="40" rows="15" required="required"></textarea></td>
			</tr>
			
		</table>
		<section id = "commandCell">
			<input type = "submit" value="등록">&nbsp;&nbsp;
			<input type = "reset" value = "다시쓰기"/>			
		</section>	
	</form>
</section>
</body>
</html>