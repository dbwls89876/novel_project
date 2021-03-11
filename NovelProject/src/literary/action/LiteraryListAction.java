package literary.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import literary.svc.LiteraryListSvc;
import vo.ActionForward;
import vo.Literary;

public class LiteraryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LiteraryListSvc literaryListSvc = new LiteraryListSvc();
		ArrayList<Literary> literaryList = literaryListSvc.getLiteraryList();
		request.setAttribute("literaryList", literaryList);
		ActionForward forward = new ActionForward("/literary/literaryList.jsp", true);
		return forward;
	}

}
