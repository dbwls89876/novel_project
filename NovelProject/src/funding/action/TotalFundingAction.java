package funding.action;

import java.util.ArrayList;
import java.util.Date;

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
		Date date = new Date();
		FundingService totalFundingService = new FundingService();
		ArrayList<Funding> fundingTotalList = totalFundingService.getFundingList();
		ArrayList<Funding> fundingList = new ArrayList<Funding>();
		long dateDif;
		int i = 0;
		ArrayList<Integer> restDate = new ArrayList<Integer>();
		for(Funding funding : fundingTotalList) {
			if(funding.getPermission()==1) {
				fundingList.add(funding);
				dateDif = fundingList.get(i).getEndDate().getTime() - date.getTime();
				dateDif /= (24*60*60*1000);
				restDate.add((int)dateDif);
				i++;
			}
			
		}
		
		request.setAttribute("fundingList", fundingList);
		request.setAttribute("restDate", restDate);
		forward = new ActionForward("/funding/totalFunding.jsp", true);
		return forward;
	}

}
