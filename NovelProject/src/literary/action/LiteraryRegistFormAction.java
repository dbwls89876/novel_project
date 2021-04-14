package literary.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class LiteraryRegistFormAction implements Action {

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
			out.println("location.href='loginForm.dir'");
			out.println("</script>");
		}else {
			if(memberID != null) {
				forward = new ActionForward();
				forward.setPath("/literary/literaryRegistForm.jsp");
			}
		}
		return forward;	
	}
}
