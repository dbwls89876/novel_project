package notic.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		int page = 1;
		int limit = 10;
		int limitPage = 10;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		int listCount = noticeListService.getListCount();
		
		int maxPage = (int)((double)listCount/limit+0.95);
		//첫번째 페이지의 글 갯수를 10개로 제한
		int startPage = (((int)((double)page/10+0.9)) - 1) * limitPage + 1;
		int endPage = startPage + 10 -1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		articleList = noticeListService.getArticleList(page, limit);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/notice/NoticeList.jsp");
		return forward;
	}

}
