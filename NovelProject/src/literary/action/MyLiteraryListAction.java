package literary.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import literary.svc.MyLiteraryListService;
import vo.ActionForward;
import vo.Literary;

public class MyLiteraryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyLiteraryListService myLiteraryListService = new MyLiteraryListService();
		ArrayList<Literary> myLiteraryList = myLiteraryListService.getLiteraryList();
		request.setAttribute("myLiteraryList", myLiteraryList);
		ActionForward forward = new ActionForward("myLiteraryList.jsp", true);
		return forward;
		}

}