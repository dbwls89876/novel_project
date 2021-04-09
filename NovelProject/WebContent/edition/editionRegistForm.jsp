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
<title>회차 등록</title>

</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<table>
	<tr>
		<td>
			<h2>회차 등록</h2>
			<form action ="editionRegist.ed" method="post" name="editionform">
			<table>
				<tr>
					<td class="td_left"><label for="title">제목</label></td>
					<td class="td_right"><input type="text" name="title" id="title" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15" required="required"></textarea></td>
				</tr>
			</table>
	<section id="commandCell">
		<input type="submit" value="등록">&nbsp;&nbsp;
		<input type="reset" value="다시쓰기"/>
	</section>
</form>
</td>
</tr>
</table>
</body>
</html>