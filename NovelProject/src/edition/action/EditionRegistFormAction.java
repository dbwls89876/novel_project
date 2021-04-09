package edition.action;

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
		int literaryID = (int) session.getAttribute("literaryID");
		EditionRegistFormService editionRegistFormService = new EditionRegistFormService();
		ArrayList<Literary> artistLiteraryList = editionRegistFormService.findId(literaryID);
		request.setAttribute("artistLiteraryList", artistLiteraryList);
		forward = new ActionForward("/edition/editionRegistForm.jsp", true);
		return forward;
	}

}
