package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;
import vo.Funding;

public class indexAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Funding> fundingList = null;
		IndexService indexService = new IndexService();
		
		fundingList = indexService.getRecentFunding();
		
		request.setAttribute("fundingList", fundingList);
		forward = new ActionForward("index.jsp",true);
		return forward;
	}

}
