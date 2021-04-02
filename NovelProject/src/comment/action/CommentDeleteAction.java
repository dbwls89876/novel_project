package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import vo.ActionForward;

public class CommentDeleteAction {
	
	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    
        int commentID = Integer.parseInt(request.getParameter("commentID"));
        
        CommentDAO commentDAO = CommentDAO.getInstance();
        boolean result = commentDAO.deleteComment(commentID);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
 
        // 정상적으로 댓글을 삭제했을경우 1을 전달한다.
        if(result) out.println("1");
        
        out.close();
        return null;
    }

}
