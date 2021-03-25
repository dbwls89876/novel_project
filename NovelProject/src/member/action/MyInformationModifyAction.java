package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MyInformationModifyService;
import vo.ActionForward;
import vo.Member;

public class MyInformationModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
			String id= request.getParameter("id");
			Member member = new Member();
			
		MyInformationModifyService myInformationModifyService = new MyInformationModifyService();
			boolean isModifySuccess = myInformationModifyService.getMember(member);
				if(isModifySuccess) {
					forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("member/myInformationView.jsp");
				}else {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정 실패');");
					out.println("history.back();");
					out.println("</script>");
				}
		
		return forward;
	}
}
