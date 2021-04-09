<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	BoardBean article = (BoardBean) request.getAttribute("article");

int boardID = (Integer) request.getAttribute("boardID");

ArrayList<CommentBean> articleList = (ArrayList<CommentBean>) request.getAttribute("articleList");
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
<title>Community Board View</title>
<style type="text/css">
#articleForm {
	width: 500px;
	height: 500px;
	border: 0px;
	margin: auto;
}

table {
	margin: auto;
	width: 1500px;
}

h3 {
	text-align: left;
}

#basicInfoArea {
	height: 100px;
	text-align: right;
}

#articleContentArea {
	color: #606E5E;
	margin-top: 40px;
	height: 400px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
}
</style>
</head>
<body>
	<table border="0">
		<tr>
			<td align="center"><br> <jsp:include page="../menuTop.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	<section id="articleForm">
		<section id="basicInfoArea">
			<h3>
				<%=article.getTitle()%></h3>
			<p>
				작성자 :
				<%=article.getMemberID()%></p>
			<p>
				작성일 :
				<%=article.getDate()%>
				| 조회수 :
				<%=article.getReadCount()%></p>
		</section>
		<section id="articleContentArea">
			<%=article.getContent()%>
		</section>
	</section>
	<section id="commandList">
		<a href="boardModifyForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
			[수정] </a>
		<a href="boardDeleteForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
			[삭제] </a>
		<a href="boardList.bo?page=<%=nowPage%>"> [목록] </a>
		&nbsp;&nbsp;
	</section>
	
	
	
	
	<section id="commentList">
		<table>
			<%
				if (articleList != null && listCount > 0) {
			%>
			<%
				for (int i = 0; i < articleList.size(); i++) {
			%>
			<tr>
				<td><%=articleList.get(i).getMemberID()%></td>
				<td><%=articleList.get(i).getDate()%></td>
				<td>
				
				
				
				<a href="boardDetail.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
				[수정] </a>
				<a href="boardDetail.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
				[삭제] </a>
				</td>
				
				
				
			</tr>
			<tr>
				<td colspan="3"><%=articleList.get(i).getContent()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</section>
	<section id="commentpageList">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="boardList.bo?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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

		<a href="boardList.bo?page=<%=a%>">[<%=a%>]
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
		<a href="boardList.bo?page=<%=nowPage + 1%>">[다음]</a>&nbsp;
		<%
			}
		%>
	</section>
	<%
		} else {
	%>
	<section id="emptyArea">
		등록된 댓글이 없습니다.
	</section>
	<%
		}
	%>
	<section id="commentWrite">
		<table>
			<tr>
				<td><%=session.getAttribute("memberID")%></td>
				<td><textarea placeholder="내용을 입력해 주세요." id="content"
							name="content" cols="40" rows="15" required="required"></textarea></td>
				<td><input type="submit" value="등록"></td>
			</tr>
		</table>
	</section>
</body>
</html>

