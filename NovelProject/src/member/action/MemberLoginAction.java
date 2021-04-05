package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberLoginService;
import vo.ActionForward;
import vo.Member;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member member = null;		
		String memberID = (request.getParameter("memberID"));
		String password = (request.getParameter("password"));
		
		MemberLoginService memberLoginService = new MemberLoginService();
		member = memberLoginService.getMember(memberID);
		if(member!=null && member.getMemberID().equals(memberID) && member.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("memberID", memberID);
			session.setAttribute("id", member.getId());
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.dir");
			
		}else if(member!=null && !member.getPassword().equals(password)) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.');");
			out.println("history.back();");
			out.println("</script>");
			
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
