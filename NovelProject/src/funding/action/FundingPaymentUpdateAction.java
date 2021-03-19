package funding.action;

import java.io.PrintWriter;

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
		int fundingID = Integer.parseInt(request.getParameter("fundingID"));
		int goodsID = Integer.parseInt(request.getParameter("goodsID"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		String memberID = (String)session.getAttribute("memberID");
		boolean isInsertSucess = false;
		boolean isUpdateSucess = false;
		boolean isCostUpdateSucess = false;
		FundingPaymentUpdateService fundingPaymentUpdateService = new FundingPaymentUpdateService();
		isInsertSucess = fundingPaymentUpdateService.setCustomer(Integer.parseInt((String)session.getAttribute("id")), goodsID);
		isUpdateSucess = fundingPaymentUpdateService.updateFundingCost(fundingID, cost);
		isCostUpdateSucess = fundingPaymentUpdateService.updateMemberCost(memberID, cost);
		if(isInsertSucess && isUpdateSucess && isCostUpdateSucess) {
			forward = new ActionForward("/funding/totalFunding.jsp", true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('fundingPaymentUpdate fail');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
