package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import member.svc.MemberJoinService;
import vo.ActionForward;
import vo.Member;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String address = request.getParameter("postCode") + request.getParameter("roadAddress") + request.getParameter("detailAddress");
		Member member = new Member();
		member.setMemberID(request.getParameter("memberID"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setNickname(request.getParameter("nickname"));
		member.setMobile(request.getParameter("mobile"));
		member.setAddress(address);
	
		MemberJoinService memberJoinService = new MemberJoinService();
		boolean isJoinSuccess = memberJoinService.joinMember(member);
		if(isJoinSuccess) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./loginForm.jsp");
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