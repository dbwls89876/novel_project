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
		CommentDAO dao = CommentDAO.getInstance();
        CommentBean comment = new CommentBean();
        
        // 파리미터 값을 가져온다.
        int boardID = Integer.parseInt(request.getParameter("boardID"));
        String memberID = request.getParameter("memberID");
        String content = request.getParameter("content");
        
        comment.setCommentID(dao.getSeq());    // 댓글 번호는 시퀀스값으로
        comment.setBoardID(boardID);
        comment.setMemberID(memberID);
        comment.setContent(content);
        
        boolean result = dao.insertComment(comment);
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;

	}

}
