package notic.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import notic.svc.NoticeModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeModifyProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		BoardBean boardBean = null;
		String realFolder="";
		String saveFolder="/images";
		int fileSize = 5*1024*1024;
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		boolean isModifySuccess = false;
		
		int noticeID = Integer.parseInt(request.getParameter("noticeID"));
		String nowPage = request.getParameter("page");
		
		boardBean = new BoardBean();
		NoticeModifyProService noticeModifyProService = new NoticeModifyProService();
		boolean isRightUser = noticeModifyProService.isArticleWriter(noticeID, (String)session.getAttribute("memberID"));

		if(!isRightUser) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			boardBean.setNoticeID(noticeID);
			boardBean.setTitle(multi.getParameter("title"));
			boardBean.setContent(multi.getParameter("content"));
			boardBean.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
			isModifySuccess = noticeModifyProService.modifyArticle(boardBean);
			
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
				forward.setPath("noticeDetail.no?noticeID="+multi.getParameter("noticeID")+"&page="+nowPage);
			}
		}
		return forward;
		
	}

	
		
}
