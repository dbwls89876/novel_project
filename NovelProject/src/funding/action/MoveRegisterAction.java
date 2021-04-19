package funding.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import funding.svc.MoveRegisterService;
import vo.ActionForward;
import vo.Literary;

public class MoveRegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		HttpSession session = request.getSession();
		String memberID = (String) session.getAttribute("memberID");
		if(memberID==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인하세요');");
			out.println("history.back();");
			out.println("</script>");
		}
		int id = (int) session.getAttribute("id");
		System.out.println(id);
		MoveRegisterService moveRegisterService = new MoveRegisterService();
		ArrayList<Literary> artistLiteraryList = moveRegisterService.findTitle(id);
		request.setAttribute("artistLiteraryList", artistLiteraryList);
		System.out.println(artistLiteraryList.get(0).getTitle());
		forward = new ActionForward("/funding/fundingRegister.jsp", true);
		return forward;
	}

}
