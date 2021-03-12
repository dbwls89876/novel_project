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
		LiteraryRegistService LiteraryRegistService = new LiteraryRegistService();
		String realFolder="";
		
		String saveFolder = "/images";
		String encType = "UTF-8";
		int maxSize = 5*1024*1024;
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		String image = multi.getFilesystemName("image");
		Literary literary = new Literary(
				Integer.parseInt(multi.getParameter("id")),
				Integer.parseInt(multi.getParameter("literaryID")),
				multi.getParameter("name"),
				multi.getParameter("content"),
				multi.getParameter("genre"),
				Double.parseDouble(multi.getParameter("score")),
				multi.getParameter("image"),
				Date date = new Date());
		boolean isRegistSuccess = LiteraryRegistService.registLiterary(literary);
		ActionForward forward = null;
		
		if(isRegistSuccess) {
			forward = new ActionForward("totalLiteraryList,lit", true);
		}else {
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
