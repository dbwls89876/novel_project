package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingRegisterService;
import vo.ActionForward;
import vo.Funding;

public class FundingRegisterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		Funding funding = null;
		FundingRegisterService fundingRegisterService = new FundingRegisterService();
		forward = new ActionForward("/funding/totalFunding.jsp", true);
		return forward;
	}

}
