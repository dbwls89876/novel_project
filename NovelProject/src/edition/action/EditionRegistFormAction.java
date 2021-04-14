package edition.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import edition.svc.EditionRegistFormService;
import vo.ActionForward;
import vo.Literary;

public class EditionRegistFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		EditionRegistFormService editionRegistFormService = new EditionRegistFormService();
		ArrayList<Literary> artistLiteraryList = editionRegistFormService.findLiterayID(id);
		request.setAttribute("artistLiteraryList", artistLiteraryList);
		forward = new ActionForward("/edition/editionRegistForm.jsp", true);
		return forward;
	}
}