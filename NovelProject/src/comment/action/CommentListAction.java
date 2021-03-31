package comment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentListService;
import vo.ActionForward;
import vo.CommentBean;
import vo.CommentPageInfo;

public class CommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<CommentBean> commentArticle = new ArrayList<CommentBean>();
		int commentPage = 1;
		int limit = 10;
		
		if(request.getParameter("commentPage") != null) {
			commentPage = Integer.parseInt(request.getParameter("commentPage"));
		}
		
		CommentListService commentListService = new CommentListService();
		int commentListCount = commentListService.getCommentListCount();
		
		commentArticle = commentListService.getCommentArticle(commentPage, limit);
		
		int commentMaxPage = (int)((double)commentListCount/limit+0.95);
		
		int commentStartPage = (((int)((double)commentPage/10+0.9)) - 1) * 10 + 1;
		int commentEndPage = commentStartPage + 10 -1;
		
		if(commentEndPage > commentMaxPage) commentEndPage = commentMaxPage;
		
		CommentPageInfo commentPageInfo = new CommentPageInfo();
		commentPageInfo.setCommentEndPage(commentEndPage);
		commentPageInfo.setCommentListCount(commentListCount);
		commentPageInfo.setCommentMaxPage(commentMaxPage);
		commentPageInfo.setCommentPage(commentPage);
		commentPageInfo.setCommentStartPage(commentStartPage);
		
		request.setAttribute("commentPageInfo", commentPageInfo);
		request.setAttribute("commentArticle", commentArticle);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/boardView.jsp");
		
		return forward;
	}

}
