package edition.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import edition.svc.EditionRegistService;

import vo.ActionForward;
import vo.Edition;

public class EditionRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		Edition edition = new Edition();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int literaryID = Integer.parseInt(request.getParameter("selectLiterary"));
		edition.setLiteraryID(literaryID);
		edition.setTitle(request.getParameter("title"));
		edition.setContent(request.getParameter("content"));
		
		EditionRegistService editionRegistService = new EditionRegistService();
		editionRegistService.registEdition(edition);
		forward = new ActionForward("/editionWriterList.ed", true);
		return forward;
	}
}
