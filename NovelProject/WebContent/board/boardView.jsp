<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.CommentPageInfo"%>
<%@page import="vo.BoardBean"%>
<%@page import="vo.CommentBean" %>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	BoardBean article = (BoardBean) request.getAttribute("article");
String nowPage = (String) request.getAttribute("page");
ArrayList<CommentBean> commentArticle = (ArrayList<CommentBean>) request.getAttribute("commentArticle");
CommentPageInfo commentPageInfo = (CommentPageInfo) request.getAttribute("commentPageInfo");
int commentListCount = commentPageInfo.getCommentListCount();
int commentNowPage = commentPageInfo.getCommentPage();
int commentMaxPage = commentPageInfo.getCommentMaxPage();
int commentStartPage = commentPageInfo.getCommentStartPage();
int commentEndPage = commentPageInfo.getCommentEndPage();
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
		<a
			href="boardModifyForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
			[수정] </a> <a
			href="boardDeleteForm.bo?boardID=<%=article.getBoardID()%>&page=<%=nowPage%>">
			[삭제] </a> <a href="boardList.bo?page=<%=nowPage%>"> [목록] </a>
		&nbsp;&nbsp;

	</section>


	<section id="commentList">
		<table>
			<%
				if (commentArticle != null && commentListCount > 0) {
			%>
			<tr id="commentTop">
				<td colspan="5">댓글</td>
			</tr>

			<%
				for (int i = 0; i < commentArticle.size(); i++) {
			%>
			<tr>
				<td><%=commentArticle.get(i).getMemberID()%></td>
				<td><%=commentArticle.get(i).getDate()%></td>
				<td><%=commentArticle.get(i).getContent()%></td>
				<td><a href="commentModifyForm.comm?commentID=
				<%=commentArticle.get(i).getCommentID()%>&commentPage=<%=commentNowPage%>">[수정]</a></td>
				<td><a href="commentDeleteForm.comm?commentID=
				<%=commentArticle.get(i).getCommentID()%>&commentPage=<%=commentNowPage%>">[삭제]</a></td>
			</tr>
			<%
				}
			%>
		</table>
	</section>
	<section id="pageList">
		<%
			if (commentNowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="commentList.comm?commentPage=<%=commentNowPage - 1%>">[이전]</a>&nbsp;
		<%
			}
		%>

		<%
			for (int a = commentStartPage; a <= commentEndPage; a++) {
			if (a == commentNowPage) {
		%>
		[<%=a%>]
		<%
			} else {
		%>

		<a href="commentList.comm?commentPage=<%=a%>">[<%=a%>]
		</a>&nbsp;
		<%
			}
		%>
		<%
			}
		%>
		<%
			if (commentNowPage >= commentMaxPage) {
		%>
		[다음]
		<%
			} else {
		%>
		<a href="commentList.comm?commentPage=<%=commentNowPage + 1%>">[다음]</a>&nbsp;
		<%
			}
		%>
		<a href="commentWriteForm.comm">글쓰기</a>
		<%
			
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
		<br>
		
	<section id="commentWrite">
		<form action="commentWritePro.comm" method="post" name="boardform">
			<table>
				<tr>
					<td colspan="3"><h3>댓글 작성하기</h3></td>
				</tr>
				<tr>
					<td class="commentLeft"><%=session.getAttribute("memberID")%></td>
					<td class="commentMiddle"><textarea placeholder="내용을 입력해 주세요." id="content"
							name="content" cols="40" rows="15" required="required"></textarea></td>
					<td class="commentRight"><input type="submit" value="등록">&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</section>

</body>
</html>

