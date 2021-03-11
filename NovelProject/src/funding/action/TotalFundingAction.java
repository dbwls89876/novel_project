package funding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingService;
import vo.ActionForward;
import vo.Funding;

public class TotalFundingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FundingService totalFundingService = new FundingService();
		ArrayList<Funding> fundingList = totalFundingService.getFundingList();
		request.setAttribute("fundingList", fundingList);
		ActionForward forward = new ActionForward("/funding/totalFunding.jsp", true);
		return forward;
	}

}
