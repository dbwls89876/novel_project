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
		ActionForward forward = null;
		FundingService totalFundingService = new FundingService();
		ArrayList<Funding> fundingTotalList = totalFundingService.getFundingList();
		ArrayList<Funding> fundingList = new ArrayList<Funding>();
		for(Funding funding : fundingTotalList) {
			if(funding.getPermission()==1) {
				fundingList.add(funding);
			}
		}
		request.setAttribute("fundingList", fundingList);
		forward = new ActionForward("/funding/totalFunding.jsp", true);
		return forward;
	}

}
