<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펀딩 등록</title>
<style type="text/css">
	#registForm{
	width: 500px;
	height:610px;
	border:1px solid red;
	margin : auto;
	}
	
	h2{
		text-align: center;
	}
	
	table{
		margin: auto;
		width: 450px;
	}
	
	.td_left{
		width:150px;
		background: orange;
	}
	
	.td_right{
		width: 300px;
		background: skyblue;
	}
	
	#commandCell{
		text-align: center;
	}
</style>
</head>
<body>
<form action="fundingRegister.fun" method="post" enctype="multipart/form-data" name="fundingForm">
<table>
	<tr>
		<td class="td_left"><label for="">신청 작품</label></td>
		<td><!-- 신청 작품 select --></td>
	</tr>
	<tr>
		<td class="td_left"><label for="">제목</label></td>
		<td class="td_right"><input type="text" name="" id="" required="required"/></td>
	</tr>
	<tr>
		<td class="td_left"><label for="">내용</label></td>
		<td><textarea id="" name="" cols="40" rows="15" required="required"></textarea></td>
	</tr>
	<tr>
		<td class="td_left"><label for="">파일 첨부</label></td>
		<td class="td_right"><input type="file" name="" id="" required="required"/></td>
	</tr>
</table>
<section id="commandCell">
	<input type="submit" value="등록">&nbsp;&nbsp;
	<input type="reset" value="다시쓰기">
</section>
</form>
</body>
</html>