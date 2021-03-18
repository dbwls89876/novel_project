package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import funding.svc.FundingPaymentUpdateService;
import vo.ActionForward;
import vo.Customer;

public class FundingPaymentUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		int goodsID = Integer.parseInt(request.getParameter("goodsID"));
		boolean isInsertSucess = false;
		
		FundingPaymentUpdateService fundingPaymentUpdateService = new FundingPaymentUpdateService();
		isInsertSucess = fundingPaymentUpdateService.setCustomer(Integer.parseInt((String)session.getAttribute("id")), goodsID);
		request.setAttribute("isInsertSucess", isInsertSucess);
		forward = new ActionForward("/funding/totalFunding.jsp", true);
		return forward;
	}

}
