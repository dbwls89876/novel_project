<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.BoardBean"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Board</title>
<style type="text/css">
.bWrite a{
	color: #606E5E;
	position: absolute;
	right: 400px;
	top: 160px;
	font-size: 16px;
	font-weight: normal;
}

.bWrite a:hover{
	text-decoration: none;
	color: #606E5E;
}

table {
	margin:65px auto;
	width: 1110px;
	text-align: center;
	font-size: 16px;
	margin-bottom: 0;
}

table a:hover{
	text-decoration: none;
	color: #606E5E;
}

#tr_top {
	background: #606E5E;
	text-align: center;
	color: white;
	font-size: 16px;
	font-weight: bold;
}

hr {
	border: 1;
	width: 1110px;
	maring:auto;
	background: #606E5E;
}

#pageList {
	maring: 50px;
	text-align: center;
}

a{
	text-decoration: none;
	color: #606E5E;
}

a:hover{
	text-decoration: none;
	color: #606E5E;
}
#emptyArea {
	text-align: center;
	margin: 100px;
}
</style>
</head>
<body>
	<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
	</div>
	<div class="container my-1">
		<div class="row">
			<div class="col-md-8">
				<h2 class="display-5 font-weight-bold" style="color:#606E5E;">커뮤니티</h2>
			</div>
		</div>
	<div class="bWrite">
		<a href="boardWriteForm.bo">글쓰기</a>
	</div>
	<section id="listForm">
		<table>
			<%
				if (articleList != null && listCount > 0) {
			%>
			<tr id="tr_top" height="40px">
				<td width="80px">번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="120px">작성일</td>
				<td width="80px">조회수</td>
			</tr>

			<%
				for(int i=0;i<articleList.size();i++) {
			%>
			<tr height="45px">
				<td><%=articleList.get(i).getBoardID()%></td>
				<td>
				<%if(articleList.get(i).getReplyLV() != 0) {%>
					<%for(int a=0; a<=articleList.get(i).getReplyLV()*2; a++) {%>
					&nbsp;
					<%} %> ↳
				<%} %>				
					<a href="boardDetail.bo?boardID=<%=articleList.get(i).getBoardID()%>&page=<%=nowPage%>">
						<%=articleList.get(i).getTitle()%></a>
				</td>
				<td><%=articleList.get(i).getMemberID()%></td>
				<td><%=articleList.get(i).getDate()%></td>
				<td><%=articleList.get(i).getReadCount()%></td>
			</tr>
			<%} %>
		</table>
	</section>
	<hr>
	<section id="pageList">
		<%if (nowPage <= 1) { %>
		&lt; 이전&nbsp;
		<%} else { %>
			<a href="boardList.bo?page=<%=nowPage - 1%>">&lt; 이전</a>&nbsp;
		<%} %>

		<%for (int a = startPage; a <= endPage; a++) {
			if (a == nowPage) { %>
				<%=a%>
		<%} else { %>

			<a href="boardList.bo?page=<%=a%>"><%=a%>
			</a>&nbsp;
			<%} %>
		<%} %>
		<%if (nowPage >= maxPage) { %>
		다음 &gt;
		<%} else { %>
			<a href="boardList.bo?page=<%=nowPage + 1%>">다음 &gt;</a>&nbsp;
		<%} %>
	</section>
	<%} else { %>
	<section id="emptyArea">
		등록된 글이 없습니다.
	</section>
	<%} %>
	</div>
</body>
</html>
