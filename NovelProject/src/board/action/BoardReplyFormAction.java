package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardModifyFormSvc;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int boardID = Integer.parseInt(request.getParameter("boardID"));
		String page = request.getParameter("page");
		
		BoardModifyFormSvc boardModifyFormSvc = new BoardModifyFormSvc();
		BoardBean article = boardModifyFormSvc.getArticle(boardID);
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		forward.setPath("/board/qna_board_reply.jsp");
		return forward;
	}

}
