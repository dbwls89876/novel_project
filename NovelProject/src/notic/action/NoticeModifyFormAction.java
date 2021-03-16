package notic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeModifyFormSvc;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		String page = request.getParameter("page");
		
		NoticeModifyFormSvc noticeModifyFormSvc = new NoticeModifyFormSvc();
		BoardBean article = noticeModifyFormSvc.getArticle(noticeID);
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		forward.setPath("/notice/NoticeModify.jsp");
		return forward;
	}

}