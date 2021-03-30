package edition.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import edition.svc.EditionDetailService;
import vo.ActionForward;
import vo.Edition;

public class EditionDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = 1;
		if(request.getParameter("num")!=null)
			num = Integer.parseInt(request.getParameter("num"));
		String page= request.getParameter("page");
		
		EditionDetailService editionDetailService = new EditionDetailService();
		Edition article = editionDetailService.getArticle(num);
		System.out.println(article.getTitle());
		System.out.println(article.getContent());
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		ActionForward forward = new ActionForward();
		forward.setPath("/edition/editionDetailView.jsp");
		return forward;
	}
}
