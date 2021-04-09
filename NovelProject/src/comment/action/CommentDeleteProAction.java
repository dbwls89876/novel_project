package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentDeleteProService;
import vo.*;

public class CommentDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		int commentID = Integer.parseInt(request.getParameter("commentID"));
		String nowPage = request.getParameter("page");
		CommentDeleteProService commentDeleteProService = new CommentDeleteProService();
		boolean isArticleWriter = commentDeleteProService.isArticleWriter(commentID, request.getParameter("memberID"));
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}else {
			boolean isDeleteSuccess = commentDeleteProService.removeArticle(commentID);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?boardID=" + article.getBoardID()+"&page="+nowPage);
			}
		}
		return forward;
	}

}
