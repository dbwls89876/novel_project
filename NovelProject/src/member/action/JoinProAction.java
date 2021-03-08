package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.JoinProSvc;
import vo.ActionForward;
import vo.Member;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("pass"));
		member.setName("name");
		member.setAge(Integer.parseInt(!(request.getParameter("age")==null
				||request.getParameter("age").equals(""))?request.getParameter("age"):"0"));
		member.setGender(request.getParameter("gender"));
		member.setEmail(request.getParameter("email"));
		JoinProSvc joinProSvc = new JoinProSvc();
		boolean isJoinSuccess = joinProSvc.joinMember(member);
		if(isJoinSuccess) {
			forward = new ActionForward();
			forward.setPath("loginForm.log");
			forward.setRedirect(true);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 등록 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
