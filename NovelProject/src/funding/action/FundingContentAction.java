package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingContentService;
import vo.ActionForward;
import vo.Funding;

public class FundingContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int literaryID = Integer.parseInt(request.getParameter("literaryID"));
		FundingContentService fundingContentService = new FundingContentService();
		Funding funding = fundingContentService.getFunding(literaryID);
		request.setAttribute("funding", funding);
		forward = new ActionForward("/funding/fundingContent.jsp", true);
		return forward;
	}

}
