<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Board</title>
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
	text-align: center;
	font-size: 16px;
	margin-bottom: 0;
}

.td_left {
	background: #606E5E;
	text-align: center;
	color: white;
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	width: 200px;
}

.tr_right {
	left: 10px;
	text-align: left;
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

</style>
</head>
<body>
	<table>
		<tr>
			<td align="center"><br> <jsp:include page="../menuTop.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<div class="nTitle">
		공지 등록하기 
	</div>
	
	<section id="writeForm">
		<form action="noticeWritePro.no" method="post"
			enctype="multipart/form-data" name="noticeform">
			<table>
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
					<td class="td_left"><label for="file">파일 첨부</label></td>
					<td><input name="file" type="file" id="file" required="required" /></td>
				</tr>

				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea placeholder="내용을 입력해 주세요." id="content"
							name="content" cols="40" rows="15" required="required"></textarea></td>
				</tr>

			</table>
			<section id="commandCell">
				<input type="button" value="목록"
					onClick="location.href='noticeList.no'">&nbsp;&nbsp; <input
					type="submit" value="등록">&nbsp;&nbsp; <input type="reset"
					value="다시쓰기" />
			</section>
		</form>
	</section>
</body>
</html>