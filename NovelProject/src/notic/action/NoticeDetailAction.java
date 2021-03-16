package notic.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int noticeID = 1;
		if(request.getParameter("noticeID")!=null) 
			noticeID = Integer.parseInt(request.getParameter("noticeID"));//볼 글의 번호
		String page = request.getParameter("page"); // 페이지 번호
		NoticeDetailService noticeDitailService = new NoticeDetailService();
		BoardBean article = noticeDitailService.getArticle(noticeID);// 번호와 일치하는 글의 정보 호출
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		forward.setPath("/notice/NoticeView.jsp");
			
		return forward;
	}

}
