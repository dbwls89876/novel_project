<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<div class="container">
	<div class="row">
		<div class="col-12 m-5"><h1 class="display-3">금액 충전</h1></div>
	</div>
	<form action="">
		<div class="form-check">
	      <label class="form-check-label" for="radio1">
	        <input type="radio" class="form-check-input" id="cash" name="cash" value="1000" onClick="this.form.cash1.disabled=true">1000원
	      </label>
	    </div>
	    <div class="form-check">
	      <label class="form-check-label" for="radio2">
	        <input type="radio" class="form-check-input" id="cash" name="cash" value="2000" onClick="this.form.cash1.disabled=true">2000원
	      </label>
	    </div>
	    <div class="form-check">
	      <label class="form-check-label">
	        <input type="radio" class="form-check-input" id="cash" name="cash" value="5000" onClick="this.form.cash1.disabled=true">5000원
	      </label>
	    </div>
	    <div class="form-check">
	      <label class="form-check-label">
	        <input type="radio" class="form-check-input" id="cash" name="cash" value="10000" onClick="this.form.cash1.disabled=true">10000원
	      </label>
	    </div>
	    <div class="form-check">
	      <label class="form-check-label">
	        <input type="radio" class="form-check-input" id="cash" name="cash" value="20000" onClick="this.form.cash1.disabled=true">20000원
	      </label>
	    </div>
	    <div class="form-check">
	      <label class="form-check-label">
	        <input type="radio" class="form-check-input" id="cash" name="cash"  onClick="this.form.cash1.disabled=false">
	       	 직접입력 : <input type="text" name="cash1" id="cash1" disabled>
	      </label>
	    </div>
	    <button type="submit" class="btn btn-success my-3">충전</button>
    </form>
</div>
</body>
</html>