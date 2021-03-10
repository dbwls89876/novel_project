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
		member.setMemberID(request.getParameter("memberID"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setNickname(request.getParameter("nickname"));
		member.setMobile(request.getParameter("mobile"));
		member.setAddress(request.getParameter("address"));
		
		
		
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
