package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		String nowPage = request.getParameter("page");
		int boardID = Integer.parseInt(request.getParameter("boardID"));
		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBean article = boardDetailService.getArticle(boardID);
		request.setAttribute("article", article);
		request.setAttribute("page", nowPage);
		forward.setPath("/board/boardReply.jsp");
		return forward;
	}

}
