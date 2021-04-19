package literary.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import literary.svc.LiteraryRegistService;
import vo.ActionForward;
import vo.Literary;

public class LiteraryRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		Literary literary = null;
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String genre = request.getParameter("genre");
		String image = request.getParameter("image");
		String id = request.getParameter("id");

		literary = new Literary();
		request.setAttribute("id", id);
		literary.setId(Integer.parseInt(id));
		literary.setTitle(title);
		literary.setContent(content);
		literary.setGenre(genre);
		literary.setImage(image);
		
		LiteraryRegistService literaryRegistService = new LiteraryRegistService();
		
		boolean isRegistSuccess = literaryRegistService.registLiterary(literary);
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