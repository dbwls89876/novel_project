package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentDetailService;
import vo.ActionForward;
import vo.CommentBean;

public class CommentDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int commentID = Integer.parseInt(request.getParameter("commentID"));
		String page = request.getParameter("page");
		CommentDetailService commentDitailService = new CommentDetailService();
		CommentBean article = commentDitailService.getArticle(commentID);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("articel", article);
		forward.setPath("board/boardView.jsp");
		return null;
	}

}
