package edition.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import edition.svc.EditionWriterListService;
import vo.ActionForward;
import vo.Edition;
import vo.PageInfo;

public class EditionWriterListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<Edition> articleList=new ArrayList<Edition>();
		int page=1;
		int limit=10;
		int limitPage=10;
				
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		EditionWriterListService editionWriterListService = new EditionWriterListService();
		int listCount = editionWriterListService.getListCount();
		//총 리스트 수를 받아옴
		articleList = editionWriterListService.getArticleList(page,limit);
		//리스트를 받아옴
		//총페이지 수
		int maxPage = (int)((double)listCount/limit+0.95);
		//0.95를 더해서 올림처리
		//현재 페이지에 보여줄 시작 페이지 수 
		int startPage = ((int)((double)page/limitPage+0.9)-1)*limitPage +1;
		int endPage = startPage + limitPage -1;
		
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
		forward.setPath("/edition/editionWriterList.jsp");
		return forward;
	}
}
