package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MyInformationDeleteService;
import vo.ActionForward;

public class MyInformationDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String memberID =(String)session.getAttribute("memberID");
		
		MyInformationDeleteService myInformationDeleteService = new MyInformationDeleteService();
		boolean isModifySuccess = myInformationDeleteService.deleteMember(memberID);
		
		if(isModifySuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("myInformationDeleteForm.me");
		}
		return forward;
	}

}
