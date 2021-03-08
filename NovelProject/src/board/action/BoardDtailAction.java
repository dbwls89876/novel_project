package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDtailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_num = 1;
		if(request.getParameter("board_num")!=null) 
			board_num = Integer.parseInt(request.getParameter("board_num"));//볼 글의 번호
		String page = request.getParameter("page"); // 페이지 번호
		BoardDetailService boardDitailService = new BoardDetailService();
		BoardBean article = boardDitailService.getArticle(board_num);// 번호와 일치하는 글의 정보 호출
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		forward.setPath("/board/qna_board_view.jsp");
			
		return forward;
	}

}
