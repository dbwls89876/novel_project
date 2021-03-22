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
		int id = (int)session.getAttribute("id");
		boolean isPaymentSucess = false;
		
		FundingPaymentUpdateService fundingPaymentUpdateService = new FundingPaymentUpdateService();
		isPaymentSucess = fundingPaymentUpdateService.paymentUpdate(id, goodsID, fundingID, memberID, cost);
		if(isPaymentSucess) {
			forward = new ActionForward("/totalFunding.fun", true);
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
