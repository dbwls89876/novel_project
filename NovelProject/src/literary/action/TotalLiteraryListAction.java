package literary.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import literary.svc.LiteraryListService;
import vo.ActionForward;
import vo.Literary;

public class TotalLiteraryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		LiteraryListService literaryListService = new LiteraryListService();
		ArrayList<Literary> literaryList = literaryListService.getLiteraryList();
		request.setAttribute("literaryList", literaryList);
		ActionForward forward = new ActionForward("/literary/totalLiteraryList.jsp", true);
		return forward;
	}

}

