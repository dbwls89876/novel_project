<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.CommentPageInfo"%>
<%@page import="vo.BoardBean"%>
<%@page import="vo.CommentBean"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	BoardBean article = (BoardBean) request.getAttribute("article");
String nowPage = (String) request.getAttribute("page");
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
<script type="text/javascript">
	var httpRequest = null;

	// httpRequest 객체 생성
	function getXMLHttpRequest() {
		var httpRequest = null;

		if (window.ActiveXObject) {
			try {
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e2) {
					httpRequest = null;
				}
			}
		} else if (window.XMLHttpRequest) {
			httpRequest = new window.XMLHttpRequest();
		}
		return httpRequest;
	}

	// 댓글 등록
	function writeCmt() {
		var form = document.getElementById("writeCommentForm");

		var board = form.comment_board.value
		var id = form.comment_id.value
		var content = form.comment_content.value;

		if (!content) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			var param = "comment_board=" + board + "&comment_id=" + id
					+ "&comment_content=" + content;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentWriteAction.co", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	function checkFunc() {
		if (httpRequest.readyState == 4) {
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				document.location.reload(); // 상세보기 창 새로고침
			}
		}
	}
</script>
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

	<!-- 댓글 부분 -->
	<section id="comment">
		<table>
			<!-- 댓글 목록 -->
			<c:if test="${requestScope.commentList != null}">
				<c:forEach var="comment" items="${requestScope.commentList}">

					<tr>
						<!-- 아이디, 작성날짜 -->
						<td width="150">
							<div>
								${boardComment.memberID}<br> <font size="2"
									color="lightgray">${boardComment.date}</font>
							</div>
						</td>
						<!-- 본문내용 -->
						<td width="550">
							<div class="commentContent">${boardComment.content}</div>
						</td>
						<!-- 버튼 -->
						<td width="100">
							<div id="btn" style="text-align: center;">
								<a href="#">[답변]</a><br>
								<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
								<c:if test="${boardComment.memberID == sessionScope.sessionID}">
									<a href="#">[수정]</a>
									<br>
									<a href="#">[삭제]</a>
								</c:if>
							</div>
						</td>
					</tr>

				</c:forEach>
			</c:if>

			<!-- 로그인 했을 경우만 댓글 작성가능 -->
			<c:if test="${sessionScope.sessionID !=null}">
				<tr bgcolor="#F5F5F5">
					<form id="commentWriteForm">
						<input type="hidden" name="boardID" value="${board.boardID}">
						<input type="hidden" name="commentID"
							value="${sessionScope.sessionID}">
						<!-- 아이디-->
						<td>
							<div>${sessionScope.sessionID}</div>
						</td>
						<!-- 본문 작성-->
						<td>
							<div>
								<textarea name="commentContent" rows="4" cols="70"></textarea>
							</div>
						</td>
						<!-- 댓글 등록 버튼 -->
						<td>
							<div id="btn" style="text-align: center;">
								<p>
									<a href="#" onclick="writeCmt()">[댓글등록]</a>
								</p>
							</div>
						</td>
					</form>
				</tr>
			</c:if>

		</table>
	</section>


</body>
</html>

