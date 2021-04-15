<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<%
session = request.getSession();
Member member = (Member)session.getAttribute("member");
%>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<h2>새 작품 등록</h2>
<form action ="literaryRegist.lit" method="post"  name="literaryform">
	<table>
		<tr>
			<td class="td_right"><input type="hidden" name="id" id="id" value=<%=request.getParameter("id") %> required="required" /></td>
		</tr>
		<tr>
			<td>닉네임 : </td>
			<td><%=member.getNickname() %></td>
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