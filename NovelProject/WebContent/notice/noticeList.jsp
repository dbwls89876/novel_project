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
<title>Notice Board</title>
<style type="text/css">
#registForm {
	width: 1500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
	color: #606E5E;
}

table {
	margin: auto;
	width: 1000px;
	text-align: center;
}

#tr_top {
	background: #606E5E;
	text-align: center;
	color: white;
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td align="center"><br> <jsp:include page="../menuTop.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<section id="listForm">
		<h2>공지사항</h2>
		<table>
			<%
				if (articleList != null && listCount > 0) {
			%>
			<tr id="tr_top">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>

			<%
				for (int i = 0; i < articleList.size(); i++) {
			%>
			<tr>
				<td><%=articleList.get(i).getNoticeID()%></td>
				<td><a
					href="noticeDetail.no?noticeID=<%=articleList.get(i).getNoticeID()%>&page=<%=nowPage%>">
						<%=articleList.get(i).getTitle()%></a></td>
				<td><%=articleList.get(i).getMemberID()%></td>
				<td><%=articleList.get(i).getDate()%></td>
				<td><%=articleList.get(i).getReadCount()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</section>
	<section id="pageList">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="noticeList.no?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
		<%
			}
		%>

		<%
			for (int a = startPage; a <= endPage; a++) {
			if (a == nowPage) {
		%>
		[<%=a%>]
		<%
			} else {
		%>

		<a href="noticeList.no?page=<%=a%>">[<%=a%>]
		</a>&nbsp;
		<%
			}
		%>
		<%
			}
		%>
		<%
			if (nowPage >= maxPage) {
		%>
		[다음]
		<%
			} else {
		%>
		<a href="noticeList.no?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>
		<a href="noticeWriteForm.no">글쓰기</a>
		<%
			
		%>

	</section>
	<%
		} else {
	%>
	<section id="emptyArea">
		등록된 글이 없습니다. <a href="noticeWriteForm.no">글쓰기</a>
	</section>
	<%
		}
	%>
</body>
</html>