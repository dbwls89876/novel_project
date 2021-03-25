package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MyInformationModifyFormService;
import member.svc.MyInformationModifyProService;
import vo.ActionForward;
import vo.Member;

public class MyInformationModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		HttpSession session = request.getSession();
		if(session.getAttribute("id")==null || !session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html'charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주세요')");
			out.println("location.href='loginForm.me'");
			out.println("</script>");
		}else {
			Member member = new Member();
			member.setMemberID(request.getParameter("memberID"));
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			member.setNickname(request.getParameter("nickname"));
			member.setMobile(request.getParameter("mobile"));
			member.setAddress(request.getParameter("address"));
			
			MyInformationModifyProService myInformationModifyProService = new MyInformationModifyProService();
			boolean isModifySuccess = myInformationModifyProService.modifyMember(member);
			
			if(isModifySuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("menuTop.jap?id="+member.getId());
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return forward;
	}

}