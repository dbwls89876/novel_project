package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.MonthFundingService;
import vo.ActionForward;

public class MonthFundingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonthFundingService fundingListService = new MonthFundingService();
		ActionForward forward = new ActionForward("funding/monthFunding", true);
		return forward;
	}
	
	
	
}
