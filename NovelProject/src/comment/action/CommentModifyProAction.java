package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentModifyProService;
import vo.ActionForward;
import vo.CommentBean;

public class CommentModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int commentID = Integer.parseInt(request.getParameter("commentID"));
		String nowPage = request.getParameter("page");
		CommentBean article = new CommentBean();
		CommentModifyProService commentModifyProService = new CommentModifyProService();
		boolean isRightUser = commentModifyProService.isArticleWriter(commentID, request.getParameter("memberID"));
		
		if(!isRightUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			article.setCommentID(commentID);
			article.setContent(request.getParameter("content"));
			isModifySuccess = CommentModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?boardID=" + article.getBoardID()+"&page="+nowPage);
			}
		}
		return forward;
	}

}