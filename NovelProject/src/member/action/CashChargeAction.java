package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.CashChargeService;
import vo.ActionForward;

public class CashChargeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int cash = 0;
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("memberID");
		CashChargeService cashChargeService = new CashChargeService();
		cash = Integer.parseInt(request.getParameter("cash"));
		if(cash<=0) {
			cash = Integer.parseInt(request.getParameter("cash1"));
		}
		
		cashChargeService.getMoney(memberID, cash);
		ActionForward forward = new ActionForward("index.dir", true);
		return forward;
	}

}
