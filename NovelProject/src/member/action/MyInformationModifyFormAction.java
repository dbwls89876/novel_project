package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;

import member.svc.MyInformationViewService;
import vo.ActionForward;
import vo.Member;

public class MyInformationModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String memberID =(String)session.getAttribute("memberID");
		
		if(memberID==null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주세요')");
			out.println("location.href='loginForm.me'");
			out.println("</script>");
		}else {
			
			
			MyInformationViewService myInformationViewService = new MyInformationViewService();
			Member member = myInformationViewService.getMember(memberID);
			if(memberID != null) {
				request.setAttribute("member", member);
				session.setAttribute("member", member);
				forward = new ActionForward();
				forward.setPath("/member/myInformationModify.jsp");
			}
		}
		return forward;	
	}
}
