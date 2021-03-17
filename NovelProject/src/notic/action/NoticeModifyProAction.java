package notic.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeModifyProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String page = request.getParameter("page");
		
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
		boolean isRightUser = noticeModifyProService.isArticleWriter(noticeID,id);
		if(!isRightUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			BoardBean article = new BoardBean();
			article.setBoardID(noticeID);
			article.setTitle(title);
			article.setContent(content);
			boolean isModifySuccess = noticeModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("noticeDetail.nono?noticeID="+noticeID+"&page="+page);
			}
		}
		return forward;
		
	}

	
		
}
