package comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import vo.ActionForward;
import vo.CommentBean;

public class CommentUpdateFormAction {

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 수정할 댓글의 글번호를 가져온다.
        int commentID = Integer.parseInt(request.getParameter("num"));
 
        CommentDAO commentDOA = CommentDAO.getInstance();
        CommentBean commentBean = commentDOA.getComment(commentID);
        
        // 댓글 정보를 request에 세팅한다.
        request.setAttribute("comment", commentBean);
        
        forward.setRedirect(false);
        forward.setPath("board/comment/CommentUpdateForm.jsp");
        
        return forward;
    }
}
