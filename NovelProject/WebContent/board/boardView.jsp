<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	function doAction(value) {
		if (value == 0) // 수정
			location.href = "BoardUpdateFormAction.bo?num=${board.boardID}&page=${pageNum}";
		else if (value == 1) // 삭제
			location.href = "BoardDeleteAction.bo?num=${board.boardID}";
	}
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
	function writeComment() {
		var form = document.getElementById("writeCommentForm");

		var boardID = form.boardID.value
		var memberID = form.memberID.value
		var content = form.content.value;

		if (!content) {
			alert("내용을 입력하세요.");
			return false;
		} else {
			var param = "boardID=" + boardID + "&memberID=" + memberID
					+ "&content=" + content;

			httpRequest = getXMLHttpRequest();
			httpRequest.onreadystatechange = checkFunc;
			httpRequest.open("POST", "CommentWriteAction.co", true);
			httpRequest.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded;charset=EUC-KR');
			httpRequest.send(param);
		}
	}

	function checkComment() {
		if (httpRequest.readyState == 4) {
			// 결과값을 가져온다.
			var resultText = httpRequest.responseText;
			if (resultText == 1) {
				document.location.reload(); // 상세보기 창 새로고침
			}
		}
	}

	// 댓글 답변창
	function commentReplyOpen(commentID) {
		var userId = '${sessionScope.sessionID}';

		if (userId == "" || userId == null) {
			alert("로그인후 사용가능합니다.");
			return false;
		} else {
			// 댓글 답변창 open
			window.name = "parentForm";
			window.open("CommentReplyFormAction.comm?num=" + commentID,
					"replyForm",
					"width=570, height=350, resizable = no, scrollbars = no");
		}
	}
	
	 // 댓글 삭제창
    function commentDeleteOpen(commentID){
        var msg = confirm("댓글을 삭제합니다.");    
        if(msg == true){ // 확인을 누를경우
            deleteComment(commentID);
        }
        else{
            return false; // 삭제취소
        }
    }

    // 댓글 삭제
    function deleteComment(commentID)
    {
        var param="commentID="+commentID;
        
        httpRequest = getXMLHttpRequest();
        httpRequest.onreadystatechange = checkFunc;
        httpRequest.open("POST", "CommentDeleteAction.comm", true);    
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
        httpRequest.send(param);
    }
    
 	// 댓글 수정창
    function commentUpdateOpen(commentID){
        window.name = "parentForm";
        window.open("CommentUpdateFormAction.comm?num="+commentID,
                    "updateForm", "width=570, height=350, resizable = no, scrollbars = no");
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
									<a href="#" onclick="cmUpdateOpen(${comment.comment_num})">[수정]</a><br>
									<a href="#" onclick="commDeleteOpen(${boardComment.commentID})">[삭제]</a>
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
									<a href="#" onclick="writeComment()">[댓글등록]</a>
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

