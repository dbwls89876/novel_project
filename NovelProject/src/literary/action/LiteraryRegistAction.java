package literary.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import literary.svc.LiteraryRegistService;
import vo.ActionForward;
import vo.Literary;

public class LiteraryRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=null;
		Literary literary=null;
		String realFolder="";
		String saveFolder="/literary/imageUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		literary = new Literary();
		literary.setTitle(multi.getParameter("title"));
		literary.setContent(multi.getParameter("content"));
		literary.setGenre(multi.getParameter("genre"));
		literary.setImage(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		LiteraryRegistService literaryRegistService = new LiteraryRegistService();
		boolean isWriteSuccess = literaryRegistService.registArticle(literary);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html'charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("myLiteraryList.lit");
		}
		
		return forward;
	}
}
