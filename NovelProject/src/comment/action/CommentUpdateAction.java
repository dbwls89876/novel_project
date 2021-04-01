package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import vo.ActionForward;
import vo.CommentBean;

public class CommentUpdateAction {

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // 파라미터를 가져온다.
        int commentID = Integer.parseInt(request.getParameter("commentID"));
        String content = request.getParameter("content");
        
        CommentDAO commentDAO = CommentDAO.getInstance();
        
        CommentBean commentBean = new CommentBean();
        commentBean.setCommentID(commentID);
        commentBean.setContent(content);
        
        boolean result = commentDAO.updateComment(commentBean);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
        
        // 정상적으로 댓글을 수정했을경우 1을 전달한다.
        if(result) out.println("1");
        
        out.close();
        
        return null;
    }


출처: https://all-record.tistory.com/146?category=733042 [세상의 모든 기록]
}
