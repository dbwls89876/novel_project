<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="vo.Literary"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#listForm {
	width: 700px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center
}

table {
	margin: auto;
	width: 550px;
}

.div_empty {
	background-color: red;
	width: 100% height: 100%;
	text-align: center;
}

#todayImageList {
	text-align: center;
}

#productImage {
	width: 150px;
	height: 150px;
	border: none;
}

#todayImage {
	width: 100px;
	height: 100px;
	border: none;
}
</style>
</head>
<body>
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>

		<c:if test="${literaryList != null }">
			<h2>
				작품 <a href="literaryRegistForm.lit">작품 등록</a>
			</h2>
			<table>
				<tr>
					<c:forEach var="literary" items="${literaryList }" varStatus="status">
						<td><a href="literaryView.lit?id=${literary.id}"> <img
								src="images/${literary.image}" id="productImage" />
						</a> 작품명 : ${literary.name}<br> 장르 : ${literary.genre}<br></td>
						<c:if test="${((status.index+1) mod 4) == 0 }">
				</tr>
				<tr>
					</c:if>
					</c:forEach>
				</tr>
			</table>
		</c:if>
		<c:if test="${literaryList == null }">
			<div class="div_empty">작품이 없습니다.</div>
		</c:if>


	</table>
</body>
</html>