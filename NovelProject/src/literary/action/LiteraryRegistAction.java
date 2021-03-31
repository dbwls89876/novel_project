package literary.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import literary.svc.LiteraryRegistService;
import vo.ActionForward;
import vo.Literary;

public class LiteraryRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		Literary literary = null;
		
		HttpSession session = request.getSession();
		literary.setId((int)session.getAttribute("id"));
		literary.setLiteraryID(Integer.parseInt(request.getParameter("literaryID")));
		literary.setNickname(request.getParameter("nickname"));
		literary.setTitle(request.getParameter("title"));
		literary.setContent(request.getParameter("content"));
		literary.setGenre(request.getParameter("genre"));
		literary.setImage(request.getParameter("image"));
		literary.setDate(java.sql.Date.valueOf(request.getParameter("date")));
		LiteraryRegistService literaryRegistService = new LiteraryRegistService();
		
		boolean isRegistSuccess = literaryRegistService.registLiterary(literary);
		if(isRegistSuccess) {
			forward = new ActionForward("myLiteraryList.lit", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}
}
