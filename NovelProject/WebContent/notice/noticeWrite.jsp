<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Board</title>
<style type="text/css">
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
	<div class="container my-1">
	<div class="row">
			<div class="col-md-8">
				<h2 class="display-5 font-weight-bold" style="color:#606E5E;">공지게시판 작성하기</h2>
			</div>
	</div>
	<div class="container">
	<div class="col-md-15">
	<section id="writeForm">
		<form action="noticeWritePro.no" method="post"
			enctype="multipart/form-data" name="noticeform">
			<table class="table">
				<tr>
					<td class="td_left" height="40px"><label for="title">제목</label></td>
					<td class="td_right"><input type="text"
						placeholder="제목을 입력해 주세요." name="title" id="title"
						required="required" /></td>
				</tr>
				<tr>
					<td class="td_left" height="40px"><label for="memberID">작성자</label></td>
					<td class="td_right"><%=session.getAttribute("memberID")%></td>
				</tr>

				<tr>
					<td class="td_left" height="40px"><label for="file">파일 첨부</label></td>
					<td><input name="file" type="file" id="file" required="required" /></td>
				</tr>

				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea placeholder="내용을 입력해 주세요." id="content"
							name="content" cols="40" rows="15" required="required"></textarea></td>
				</tr>

			</table>
			<section id="commandCell">
			 	<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="button" value="목록"
					onClick="location.href='noticeList.no'">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" />
			</section>
		</form>
	</section>
	</div>
	</div>
	</div>
</body>
</html>