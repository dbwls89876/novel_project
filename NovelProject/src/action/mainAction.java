package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.Funding;
import vo.Literary;

public class mainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Funding> fundingList = null;
		ArrayList<Literary> literaryList = null;
		mainService indexService = new mainService();
		
		fundingList = indexService.getRecentFunding();
		literaryList = indexService.getRecentLiterary();
		
		request.setAttribute("literaryList", literaryList);
		request.setAttribute("fundingList", fundingList);
		forward = new ActionForward("main.jsp",true);
		return forward;
	}

}
