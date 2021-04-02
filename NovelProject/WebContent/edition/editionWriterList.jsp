<%@ page import="vo.PageInfo" %>
<%@ page import="vo.Edition" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	ArrayList<Edition> articleList=(ArrayList<Edition>)request.getAttribute("articleList");
	PageInfo pageInfo =(PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>회차 목록</title>
<style type="text/css">
#registForm {
	width:500px;
	height:600px;
	border:1px solid red;
	margin: auto;
}

h2{
	text-align:center;
}

table{
	margin:auto;
	width:450px;
}

#tr_top{
	background: orange;
	text-align: center;
}

#pageList {
	margin: auto;
	width:500px;
	text-align: center;
}
</style>
</head>
<body>
<div class="container p-3 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>

	<!-- 게시판 리스트 -->
	<section id="listForm">
		<h2>
			글 목록<a href="editionRegistForm.ed">작품등록</a>
		</h2>
		<table>
			<%
			if(articleList != null && listCount > 0){
			%>
				<tr id="tr_top">
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				
				<%
				for(int i=0; i<articleList.size(); i++){
				
				%>
				<tr>
					<td><%=articleList.get(i).getEditionID() %></td>
					<td><a href="editionDetailView.ed?id=<%=articleList.get(i).getEditionID() %>&page=<%=nowPage%>">
					<%=articleList.get(i).getTitle() %></a>
					<td><%=articleList.get(i).getDate() %></td>
					<td><%=articleList.get(i).getCount() %></td>
				</tr>
				<%} %>
		</table>
	</section>
	<section id="pageList">
		<%if(nowPage<=1){ %>
			[이전]&nbsp;
		<%}else{ %>
			<a href="editionWriterList.ed?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>
		<%for(int a=startPage;a<=endPage; a++){
			if(a==nowPage){%>
				[<%=a %>]
			<%}else{ %>
			
				<a href="editionWriterList.ed?page=<%=a %>">[<%=a %>]</a>&nbsp;
			<%} %>
		<%} %>
		<%if(nowPage>=maxPage){ %>
			[다음]
		<%}else { %>
			<a href="editionWriterList.ed?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>
					
	</section>
	<%
	}
	else
	{
	%>
		<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
	}
%>

</body>
</html>