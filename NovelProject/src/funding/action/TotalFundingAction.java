package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.TotalFundingService;
import vo.ActionForward;

public class TotalFundingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TotalFundingService totalFundingService = new TotalFundingService();
		ActionForward forward = new ActionForward("funding/monthFunding", true);
		return forward;
	}

}
