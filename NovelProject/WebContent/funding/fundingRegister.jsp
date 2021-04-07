<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>SummerNoteExample</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="../css/summernote/summernote-lite.js"></script>
  <script src="../css/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="../css/summernote/summernote-lite.css">
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
		var frmTag1 = "<input type='text' name='name' id='name' required='required'/>";
		var frmTag2 = "<input type='text' name='cost' id='cost' required='required'/>";
		var frmTag3 = "<input type='text' name='maxNumber' id='maxNumber' required='required'/>";
		frmTag3 += "<input type=button value='삭제' onClick='removeRow()' stype='cursor:hand'>";
		Cell1.innerHTML = frmTag1;
		Cell2.innerHTML = frmTag2;
		Cell3.innerHTML = frmTag3;
	}
	function removeRow(){
		table.deleteRow(table.clickedRowIndex);
	}
</script>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>

<div class="container">
	<textarea class="summernote" name="editordata"></textarea>
</div>
<script>
$('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
	});
</script>
<!--<table>
	<tr>
		<td>
			<form action="fundingRegister.fun" method="post" name="fundingForm">
			<table>
				<tr>
					<td class="td_left"><label for="selectLiterary">작품 선택</label></td>
					<td>
						<select name="selectLiterary" id="selectLiterary">
							<c:forEach var = "artistLiterary" items="${artistLiteraryList }" varStatus = "status">
								<option value="${artistLiterary.literaryID }">${artistLiterary.title }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">제목</label></td>
					<td class="td_right"><input type="text" name="title" id="title" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="image">이미지 첨부</label></td>
					<td class="td_right"><input type="file" name="image" id="image" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">목표 비용</label></td>
					<td class="td_right"><input type="text" name="targetCost" id="targetCost" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">마감 날짜</label></td>
					<td class="td_right"><input type="text" name="endDate" id="endDate" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="title">전달 날짜</label></td>
					<td class="td_right"><input type="text" name="deliveryDate" id="deliveryDate" required="required"/></td>
				</tr>
			</table>
			<table id="fundingGoodsTable">
				<tr>
					<td>굿즈 이름</td>
					<td>비용</td>
					<td>최대 수량</td>
				</tr>
				<tr>
					<td><input type="text" name="name" id="name" required="required"/></td>
					<td><input type="text" name="cost" id="cost" required="required"/></td>
					<td><input type="text" name="maxNumber" id="maxNumber" required="required"/></td>
				</tr>
			</table>
			<section id="commandCell">
			<input name="addButton" type="button" style="curosr:pointer" onclick="insRow()" value="굿즈 추가">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
			</section>
			</form>
		</td>
	</tr>
</table>
-->
</body>
</html>