package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String page = request.getParameter("page");
		int boardID = Integer.parseInt(request.getParameter("boardID"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
		
		boolean isArticleWriter = boardDeleteProService.isArticleWriter();
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(boardID);
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				forward = new ActionForward("boardList.bo?page=" + page, true);
			}
		}
		return forward;
	}

}
