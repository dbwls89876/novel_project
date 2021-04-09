package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentDetailService;
import vo.ActionForward;
import vo.CommentBean;

public class CommentModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		int commentID = Integer.parseInt(request.getParameter("commentID"));
		
		CommentDetailService commentDetailService = new CommentDetailService();
		CommentBean article = commentDetailService.getArticle(commentID);
		request.setAttribute("article", article);
		forward.setPath("/board/voardView.jsp");
		return forward;
	}

}
