package comment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import comment.svc.CommentListService;
import vo.ActionForward;
import vo.CommentBean;
import vo.PageInfo;

public class CommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<CommentBean> articleList = new ArrayList<CommentBean>();
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		CommentListService commentListService = new CommentListService();
		int listCount = commentListService.getListCount();
		
		articleList = commentListService.getArticleList(page, limit);
		
		int maxPage = (int)((double)listCount/limit+0.95);
		
		int startPage = (((int)((double)page/10+0.9)) -1) * 10 + 1;
		int endPage = startPage +10 -1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/boardView.jsp");
		return forward;
	}

}
