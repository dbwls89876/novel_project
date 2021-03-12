package funding.action;

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
		int id = (int) session.getAttribute("id");
		MoveRegisterService moveRegisterService = new MoveRegisterService();
		ArrayList<Literary> artistLiteraryList = moveRegisterService.findTitle(id);
		request.setAttribute("artistLiteraryList", artistLiteraryList);
		forward = new ActionForward("/funding/fundingRegster.jsp", true);
		return forward;
	}

}
