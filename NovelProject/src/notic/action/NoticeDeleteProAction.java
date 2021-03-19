package notic.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notic.svc.NoticeDeleteProService;
import vo.ActionForward;

public class NoticeDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		String nowPage = request.getParameter("page");
		NoticeDeleteProService noticeDeleteProService = new NoticeDeleteProService();
		boolean isArticleWriter = noticeDeleteProService.isArticleWriter(noticeID, Integer.parseInt(request.getParameter("id")));

		if(!isArticleWriter) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}else {
			boolean isDeleteSuccess = noticeDeleteProService.removeArticle(noticeID);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("noticeList.bo?page=" + nowPage);
			}
		}
		return forward;
	}

}
