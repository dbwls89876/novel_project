<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<style type="text/css">
table {
	margin: auto;
	width: 900px;
}

td h3 {
	text-align: left;
	color: #606E5E;
}

.td_left {
	width: 120px;
	text-align: right;
	font: bold 15px "맑은 고딕", arial;
	color: #606E5E;
	padding : 5px 20px;
	hight : 30px;
}

.td_right {
	width: 300px;
	hight : 30px;
}

#commandCell {
	text-align: center;
	padding: 20px;
}

#title {
	height: 25px;
	width: 295px;
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
	<section id="writeForm">
		<form action="boardWritePro.bo" method="post" name="boardform">
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
					<td class="td_right"><%=session.getAttribute("memberID") %></td>
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