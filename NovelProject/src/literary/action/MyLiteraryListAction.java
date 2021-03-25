package literary.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import literary.svc.MyLiteraryListService;
import vo.ActionForward;
import vo.Literary;

public class MyLiteraryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		MyLiteraryListService myLiteraryListService = new MyLiteraryListService();
		ArrayList<Literary> myLiteraryList = myLiteraryListService.getLiteraryList(id);
		request.setAttribute("myLiteraryList", myLiteraryList);
		ActionForward forward = new ActionForward("/literary/myLiteraryList.jsp", true);
		return forward;
		}

}