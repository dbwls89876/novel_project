package notic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		
		NoticeDetailService noticeDetailService = new NoticeDetailService();
		BoardBean article = noticeDetailService.getArticle(noticeID);
		request.setAttribute("article", article);
		forward.setPath("/notice/noticeModify.jsp");
		return forward;
	}

}
