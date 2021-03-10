package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberModProSvc;
import vo.ActionForward;
import vo.Member;

public class MemberModProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id")==null ||
				!session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요');");
			out.println("location.href='loginForm.log'");
			out.println("</script>");
		}else {
			Member member = new Member();
			member.setId(Integer.parseInt(request.getParameter("id")));
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			
			
			MemberModProSvc memberModProSvc = new MemberModProSvc();
			boolean isModifySuccess = memberModProSvc.modifyMember(member);
			
			if(isModifySuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memerInfo.mem?id=" + member.getId());
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return forward;
	}

}
