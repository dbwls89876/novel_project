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
		int boardID = Integer.parseInt(request.getParameter("boardID"));
		String nowPage = request.getParameter("page");
		BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
		boolean isArticleWriter = boardDeleteProService.isArticleWriter(boardID, Integer.parseInt(request.getParameter("id")));		
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}else {
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(boardID);
			
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
				forward.setPath("boardList.bo?page=" + nowPage);
			}
		}
		return forward;
	}

}
