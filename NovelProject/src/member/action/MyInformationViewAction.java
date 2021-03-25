package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MyInformationViewService;
import vo.ActionForward;
import vo.Member;

public class MyInformationViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		 	
		HttpSession session=request.getSession();
		String memberID=(String)session.getAttribute("memberID");
		
		ActionForward forward = null;
		if(memberID==null){
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./loginForm.me");
		}
		
		else{
			forward = new ActionForward();
			MyInformationViewService myInformationViewService = new MyInformationViewService();
			Member member=myInformationViewService.getMember(memberID);
			request.setAttribute("member", member);
			forward.setPath("member/myInformationView.jsp");
		}
			return forward;
	}
}