package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int boardID = Integer.parseInt(request.getParameter("boardID"));
		String page = request.getParameter("page"); // 페이지 번호
		BoardDetailService boardDitailService = new BoardDetailService();
		BoardBean article = boardDitailService.getArticle(boardID);// 번호와 일치하는 글의 정보 호출
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		forward.setPath("/board/boardView.jsp");
		        
        // 파라미터로 넘어온 글번호를 가져온다.
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
        
        String pageNum = request.getParameter("pageNum");
        
        dao.BoardDAO boardDAO = dao.BoardDAO.getInstance();
        BoardBean boardBean = boardDAO.getDetail(boardID);
        boolean result = boardDAO.updateCount(boardID);
        
        // 게시글 번호를 이용하여 해당 글에 있는 댓글 목록을 가져온다.
        dao.CommentDAO commentDAO = dao.CommentDAO.getInstance();
        ArrayList<vo.CommentBean> commentList = commentDAO.getCommentList(boardNum);
        
        // 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
        if(commentList.size() > 0)    request.setAttribute("commentList", commentList);
        
        request.setAttribute("board", boardBean);
        request.setAttribute("pageNum", pageNum);
            
        if(result){
            forward.setRedirect(false); // 단순한 조회이므로
            forward.setPath("BoardDetailForm.bo");
        }
			
		return forward;
	}

}