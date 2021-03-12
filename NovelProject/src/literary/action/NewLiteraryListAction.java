package literary.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import literary.svc.LiteraryListService;
import vo.ActionForward;
import vo.Literary;

public class NewLiteraryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Date date = new Date();
		
		LiteraryListService literaryListSvc = new LiteraryListService();
		ArrayList<Literary> literaryList = literaryListSvc.getLiteraryList();
		ArrayList<Literary> newliteraryList = new ArrayList<Literary>();
		
		for(int i = 0; i<literaryList.size(); i++) {
			long dateDif = date.getTime()- literaryList.get(i).getDate().getTime();
			dateDif /= (24*60*60*1000);
			System.out.println(dateDif);
			if(dateDif<8) {
				newliteraryList.add(literaryList.get(i));
			}
		}
		request.setAttribute("literaryList", literaryList);
		ActionForward forward = new ActionForward("/literary/newLiteraryList.jsp", true);
		return forward;
	
	}
}
