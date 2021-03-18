package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import funding.svc.FundingPaymentService;
import vo.ActionForward;
import vo.Funding;
import vo.FundingGoods;
import vo.Member;

public class FundingPaymentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		FundingPaymentService fundingPaymentService = new FundingPaymentService();
		Funding funding = fundingPaymentService.getFunding(Integer.parseInt(request.getParameter("literaryID")));
		Member member = fundingPaymentService.getMember(Integer.parseInt((String) session.getAttribute("id")));
		FundingGoods fundingGoods = fundingPaymentService.getFundingGoods(Integer.parseInt(request.getParameter("goodsID")));

		request.setAttribute("funding", funding);
		request.setAttribute("member", member);
		forward = new ActionForward("/funding/fundingPayment.jsp", true);
		return forward;
	}

}
