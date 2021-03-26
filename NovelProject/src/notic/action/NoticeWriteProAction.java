package notic.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import notic.svc.NoticeWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=null;
		BoardBean boardBean = null;
		String realFolder="";
		String saveFolder="/images";
		int fileSize = 5*1024*1024;
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		boardBean = new BoardBean();
		boardBean.setMemberID((String)session.getAttribute("memberID"));
		boardBean.setTitle(multi.getParameter("title"));
		boardBean.setContent(multi.getParameter("content"));
		boardBean.setFile(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		NoticeWriteProService noticeWriteProService = new NoticeWriteProService();
		boolean isWriteSuccess = noticeWriteProService.registArticle(boardBean);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("noticeList.no");
		}
		
		return forward;
	}

}
