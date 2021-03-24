package edition.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import edition.svc.EditionRegistService;
import vo.ActionForward;
import vo.Edition;

public class EditionRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward=null;
		Edition edition=null;
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		edition = new Edition();
		edition.setId(Integer.parseInt(multi.getParameter("id")));
		edition.setLiteraryID(Integer.parseInt(multi.getParameter("literaryID")));
		edition.setEditionID(Integer.parseInt(multi.getParameter("editionID")));
		edition.setTitle(multi.getParameter("title"));
		edition.setContent(multi.getParameter("content"));
		edition.setCount(Integer.parseInt(multi.getParameter("count")));
		
		EditionRegistService editionRegistService = new EditionRegistService();
		boolean isWriteSuccess = EditionRegistService.registArticle(edition);
		
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
			forward.setPath("editionRegistForm.ed");
		}
		
		return forward;
	}

}
