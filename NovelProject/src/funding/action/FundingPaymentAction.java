package funding.action;

import java.io.PrintWriter;

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
		String memberID = (String) session.getAttribute("memberID");
		if(memberID==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			FundingPaymentService fundingPaymentService = new FundingPaymentService();
			String literaryID = request.getParameter("literaryID");
			int cost = Integer.parseInt(request.getParameter("cost"));
					
			
			Funding funding = fundingPaymentService.getFunding(Integer.parseInt(literaryID));
			Member member = fundingPaymentService.getMember(memberID);
			FundingGoods fundingGoods = fundingPaymentService.getFundingGoods(Integer.parseInt(request.getParameter("goodsID")));
	
			request.setAttribute("funding", funding);
			request.setAttribute("member", member);
			request.setAttribute("fundingGoods", fundingGoods);
			System.out.println(member.getMoney());
			System.out.println("cost : " + cost);
			System.out.println("member : " + memberID);
			System.out.println("nick : " + member.getNickname());
			if(cost>member.getMoney()) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('후원할 수 있는 금액이 모자릅니다.');");
				out.println("history.back();");
				out.println("</script>");
			}else {
			forward = new ActionForward("/funding/fundingPayment.jsp", true);				
			}
		}
		
		return forward;
	}

}
