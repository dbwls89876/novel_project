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
		EditionDetailService editionDetailService = new EditionDetailService();
		int id = Integer.parseInt(request.getParameter("id"));
		Edition edition = editionDetailService.getEditionDetail(id);
		request.setAttribute("edition", edition);
		ActionForward forward = new ActionForward("edition/editionDetailView.jsp", false);
		return forward;
	}

}
