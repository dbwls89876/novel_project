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
		int editionID = 1;
		if(request.getParameter("editionID")!=null)
			editionID = Integer.parseInt(request.getParameter("editionID"));
		String page= request.getParameter("page");
		
		EditionDetailService editionDetailService = new EditionDetailService();
		Edition eidition = editionDetailService.getArticle(editionID);
		request.setAttribute("eidition", eidition);
		request.setAttribute("page", page);
		ActionForward forward = new ActionForward();
		forward.setPath("/edition/editionDetailView.jsp");
		return forward;
	}
}
