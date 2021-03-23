package literary.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import literary.svc.LiteraryRegistService;
import vo.ActionForward;
import vo.Literary;

public class LiteraryRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LiteraryRegistService LiteraryRegistService = new LiteraryRegistService();
		String realFolder="";
		String saveFolder="/literary/imageUpload";
		String encType = "UTF-8";
		int fileSize=5*1024*1024;
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi=new MultipartRequest(request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		String image = multi.getFilesystemName("image");

		Literary literary = new Literary(
				(int)session.getAttribute("id"), 
				0, 
				multi.getParameter("title"),
				multi.getParameter("content"),
				multi.getParameter("genre"), 
				image);
		boolean isRegistSuccess = LiteraryRegistService.registLiterary(literary);
		ActionForward forward = null;

		if(isRegistSuccess) {
			forward = new ActionForward("myLiteraryList.lit", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}

		
		return forward;
	}
}
