package funding.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingService;
import vo.ActionForward;
import vo.Funding;

public class weekFundingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		Date date = new Date();

		FundingService fundingService = new FundingService();
		ArrayList<Funding> fundingList = fundingService.getFundingList();
		ArrayList<Funding> fundingPermissionList = new ArrayList<Funding>();
		ArrayList<Funding> weekFundingList = new ArrayList<Funding>();
		
		for(int i = 0; i<fundingList.size(); i++) {
			if(fundingList.get(i).getPermission()==1)
				fundingPermissionList.add(fundingList.get(i));
		}
		
		for(int i = 0; i<fundingPermissionList.size(); i++) {
			//현재날짜 - 시작날짜, 양수
			long dateDif = date.getTime()- fundingPermissionList.get(i).getStartDate().getTime();
			//24시간
			dateDif /= (24*60*60*1000);
			System.out.println(dateDif);
			if(dateDif<8) {
				weekFundingList.add(fundingPermissionList.get(i));
			}
		}
		
		request.setAttribute("weekFundingList", weekFundingList);
		ActionForward forward = new ActionForward("funding/weekFunding.jsp", true);
		return forward;
	}
	
	
	
}
