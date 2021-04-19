<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>SummerNoteExample</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="${pageContext.request.contextPath}/css/summernote/summernote-lite.js"></script>
  <script src="${pageContext.request.contextPath}/css/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/summernote/summernote-lite.css">

  <!--  -->
  
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
		border-collapse: separate;
  		border-spacing: 0 10px;
	}
	#commandCell{
		text-align: center;
	}
</style>
</head>
<script>
	var count=1;
	var table;
	function insRow(){
		table = document.getElementById("fundingGoodsTable");
		var Row = table.insertRow();
		Row.onmouseover = function(){
			table.clickedRowIndex = this.rowIndex
		};
		var Cell1 = Row.insertCell();
		var Cell2 = Row.insertCell();
		var Cell3 = Row.insertCell();
		var frmTag1 = " <input type='text' name='name' id='name' required='required'/>";
		var frmTag2 = " <input type='text' name='cost' id='cost' required='required'/>";
		var frmTag3 = " <input type='text' name='maxNumber' id='maxNumber' required='required'/>";
		frmTag3 += "  <input type=button class='btn btn-success' value='삭제' onClick='removeRow()' stype='cursor:hand'>";
		Cell1.innerHTML = frmTag1;
		Cell2.innerHTML = frmTag2;
		Cell3.innerHTML = frmTag3;
	}
	function removeRow(){
		table.deleteRow(table.clickedRowIndex);
	}

</script>
<body>

<div class="container mt-5">
	<div class="row">
		<button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath }/totalFunding.fun'">돌아가기</button>
	</div>
</div>
<form action="fundingRegister.fun" method="post" name="fundingForm" enctype="multipart/form-data">
<div class="container h-100 grayBox">
	<div class="row m-5 p-5 justify-content-center align-self-center">
		<h1 class="dislpay-3">펀딩 등록</h1>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2 "><label for="selectLiterary">작품 선택 : </label></div>
		<div class="col-md-2"><select name="selectLiterary" id="selectLiterary" style="width:200px">
			<c:forEach var = "artistLiterary" items="${artistLiteraryList }" varStatus = "status">
				<option value="${artistLiterary.literaryID }">${artistLiterary.title }</option>
			</c:forEach>
		</select>
		</div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		
		<div class="col-md-2"><label for="title">제목 : </label></div>
		<div class="col-md-2"><input type="text" name="title" id="title" required="required"/></div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<h6 sytle="color:gray;">※그림첨부안됨</h6>
	</div>
	<div class="row justify-content-center align-self-center">
		<textarea class="summernote" id="summernote" name="content" placeholder="※이미지 첨부 기능x"></textarea>	
	</div>	
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2">
			<label for="image">이미지 첨부</label>
		</div>
		<div class="col-md-2">
			<input type="file" name="file" id="file" required="required"/>
		</div>
	</div>
	
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2">
			<label for="title">목표 비용</label>
		</div>
		<div class="col-md-2">
			<input type="text" name="targetCost" id="targetCost" required="required"/>
		</div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2">
			<label for="title">마감 날짜</label>
		</div>
		<div class="col-md-2">
			<input type="text" name="endDate" id="endDate" required="required"/>
		</div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<div class="col-md-2">
			<label for="deliveryDate">전달 날짜</label>
		</div>
		<div class="col-md-2">
			<input type="text" name="deliveryDate" id="deliveryDate" required="required"/>
		</div>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<table id="fundingGoodsTable">
				<tr>
					<td>굿즈 이름</td>
					<td>비용</td>
					<td>최대 수량</td>
				</tr>
				<tr>
					<td> <input type="text" name="name" id="name" required="required"/></td>
					<td> <input type="text" name="cost" id="cost" required="required"/></td>
					<td> <input type="text" name="maxNumber" id="maxNumber" required="required"/></td>
				</tr>
			</table>
	</div>
	<div class="row my-3 justify-content-center align-self-center">
		<section id="commandCell">
			<input name="addButton" type="button" style="curosr:pointer" onclick="insRow()" value="굿즈 추가">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
			</section>
	</div>
</div>
</form>
<script>
$('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
	});
</script>
</body>
</html>