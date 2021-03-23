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
	#listForm{
		width: 1500px;
		height: 500px;
		border: 1px solid red;
		margin: auto;
	}
	table{
		margin: auto;
		width: 1500px;
		border:1px solid;
	}

	#literaryImage {
		width: 150px;
		height: 150px;
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
	<tr>
		<td>
		<a href="${pageContext.request.contextPath }/literaryRegistForm.lit">작품 등록하기</a>
		</td>
	</tr>
	<tr>
		<td>
			<table>
			<c:forEach var="literary" items="${literaryList }" varStatus="status">
				<tr>
					<td class="literaryImage" rowspan="2">
						<a href="editionReaderView.ed?id=${literary.id}"></a>
							<img src="images/${literary.image}" class="literaryImage" />
					</td>
					<td>${literary.title}<br> ${literary.genre}<br></td>
				</tr>
			</c:forEach>
			</table>
		</td>
	</tr>
</table>
</body>
</html>