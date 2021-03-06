package edition.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import edition.svc.EditionRegistService;

import vo.ActionForward;
import vo.Edition;

public class EditionRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		Edition edition = null;
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String literaryID = request.getParameter("literaryID");
		
		edition = new Edition();
		request.setAttribute("literaryID", literaryID);
		edition.setLiteraryID(Integer.parseInt(literaryID));
		edition.setTitle(title);
		edition.setContent(content);
		EditionRegistService editionRegistService = new EditionRegistService();

		boolean isWriteSuccess = editionRegistService.registEdition(edition);
		if(isWriteSuccess) {
			forward = new ActionForward("editionWriterList.ed", true);
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
