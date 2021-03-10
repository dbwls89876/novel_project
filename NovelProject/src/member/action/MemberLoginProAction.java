package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.LoginProSvc;
import vo.ActionForward;
import vo.Member;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member member = new Member();		
		String memberID = (request.getParameter("memberID"));
		String password = (request.getParameter("password"));
		LoginProSvc loginProSvc = new LoginProSvc();
		
		member = loginProSvc.getMember(memberID);
		if(member.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("memberID", memberID);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.jsp");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
