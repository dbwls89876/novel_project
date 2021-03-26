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
		if(session.getAttribute("id")==null) {
			response.setContentType("text/html'charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!')");
			out.println("location.href='loginForm.log'");
			out.println("</script>");
		}else {
			String id = request.getParameter("id");
			MyInformationDeleteService myInformationDeleteService = new MyInformationDeleteService();
			boolean isDeleteSuccess = myInformationDeleteService.deleteMember(id);
			if(isDeleteSuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberList.mem");
			}else {
				response.setContentType("text/html'charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
			}
			
		}
		
		return forward;
	}

}
