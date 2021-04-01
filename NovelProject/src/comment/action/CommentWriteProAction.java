package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.CommentDAO;
import vo.ActionForward;
import vo.CommentBean;

public class CommentWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO commentDAO = CommentDAO.getInstance();
        CommentBean commentBean = new CommentBean();
        
        // 파리미터 값을 가져온다.
        int boardID = Integer.parseInt(request.getParameter("boardID"));
        String memberID = request.getParameter("memberID");
        String content = request.getParameter("content");
        
        commentBean.setCommentID(commentDAO.getSeq());    // 댓글 번호는 시퀀스값으로
        commentBean.setBoardID(boardID);
        commentBean.setMemberID(memberID);
        commentBean.setContent(content);
        
        boolean result = commentDAO.insertComment(commentBean);
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;

	}

}
