package funding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingContentService;
import vo.ActionForward;
import vo.Funding;
import vo.FundingGoods;

public class FundingContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		ArrayList<FundingGoods> fundingGoodsList = null;
		Funding funding = null;
		int literaryID = Integer.parseInt(request.getParameter("literaryID"));
		int fundingID = Integer.parseInt(request.getParameter("fundingID"));
		
		FundingContentService fundingContentService = new FundingContentService();
		funding = fundingContentService.getFunding(fundingID);
		fundingGoodsList = fundingContentService.getFundingGoodsList(fundingID);
		long dateDif = funding.getEndDate().getTime() - funding.getStartDate().getTime();
		dateDif /= (24*60*60*1000);
		request.setAttribute("funding", funding);
		request.setAttribute("fundingGoodsList", fundingGoodsList);
		request.setAttribute("restTime", dateDif);
		forward = new ActionForward("/funding/fundingContent.jsp", true);
		return forward;
	}

}
