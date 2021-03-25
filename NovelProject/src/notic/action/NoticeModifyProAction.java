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
		boolean isModifySuccess = false;
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		String nowPage = request.getParameter("page");
		BoardBean article = new BoardBean();
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
		boolean isRightUser = noticeModifyProService.isArticleWriter(noticeID, request.getParameter("memberID"));

		if(!isRightUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			article.setBoardID(noticeID);
			article.setTitle(request.getParameter("title"));
			article.setContent(request.getParameter("content"));
			isModifySuccess = noticeModifyProService.modifyArticle(article);
			
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
				forward.setPath("noticeDetail.no?noticeID="+article.getNoticeID()+"&page="+nowPage);
			}
		}
		return forward;
		
	}

	
		
}
