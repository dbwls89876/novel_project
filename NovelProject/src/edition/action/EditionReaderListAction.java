package edition.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import edition.svc.EditionListService;
import vo.ActionForward;
import vo.Edition;
import vo.PageInfo;

public class EditionReaderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page=1;
		int limit=10;
		int limitPage=10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		EditionListService editionListService = new EditionListService();
		int listCount = editionListService.getListCount();
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = ((int)((double)page/limitPage+0.9)-1)*limitPage +1;
		int endPage = startPage + limitPage -1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		ArrayList<Edition> articleList=new ArrayList<Edition>();
		articleList = editionListService.getArticleList(page, limit);
		System.out.println(articleList );
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/edition/editionReaderList.jsp");
		return forward;
		}
	}
