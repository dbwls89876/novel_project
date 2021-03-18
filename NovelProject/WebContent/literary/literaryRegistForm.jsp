<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
<h2>새 작품 등록</h2>
<form action ="LiteraryRegistAction.lit" method="post" enctype="multipart/form-data" name="literaryform">
	<table>
		<tr>
			<td class="td_left"><label for="nickname">글쓴이</label></td>
			<td class="td_right"><input type="hidden" name="nickname" value='${member.nickname }'/></td>
		</tr>
		<tr>
			<td class="td_left"><label for="title">제목</label></td>
			<td class="td_right"><input type="text" name="title" id="title" required="required" /></td>
		</tr>
		<tr>
			<td class="td_left"><label for="content">내용</label></td>
			<td><textarea id="content" name="content" cols="40" rows="15" required="required"></textarea></td>
		</tr>
		<tr>
			<td class="td_left"><label for="genre">장르</label></td>
			<td class="td_right"><input type="text" name="genre" id="genre" required="required"/></td>
		</tr>
		<tr>
			<td class="td_left"><label for="image">이미지</label></td>
			<td class="td_right"><input type="file" name="image" id="image" required="required" /></td>
		</tr>
	</table>
	<section id="commandCell">
		<input type="submit" value="등록">&nbsp;&nbsp;
		<input type="reset" value="다시쓰기"/>
	</section>
</form>
</body>
</html>