package notic.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardReplyProService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String page = request.getParameter("page");
		BoardBean article = new BoardBean();
		article.setBoardID(Integer.parseInt(request.getParameter("boardID")));
		article.setId(Integer.parseInt(request.getParameter("id")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("Content"));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setLev(Integer.parseInt(request.getParameter("lev")));
		article.setSeq(Integer.parseInt(request.getParameter("seq")));
		BoardReplyProService boardReplyProService = new BoardReplyProService();
		boolean isReplySuccess = boardReplyProService.replyArticle(article);
		
		
		if(isReplySuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.bo?page"+page);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답장실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
