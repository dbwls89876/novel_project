package comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import vo.ActionForward;
import vo.CommentBean;

public class CommentReplyAction {

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // 파라미터를 가져온다.
        int commentID = Integer.parseInt(request.getParameter("commentID"));
        int boardID = Integer.parseInt(request.getParameter("boardID"));
        String memberID = request.getParameter("memberID");
        String content = request.getParameter("content");
 
        CommentDAO commentDAO = CommentDAO.getInstance();
        
        CommentBean commentBean = new CommentBean();    
        commentBean.setCommentID(commentDAO.getSeq());    // 시퀀스를 가져와 세팅한다
        commentBean.setBoardID(boardID);
        commentBean.setMemberID(memberID);
        commentBean.setContent(content);
        commentBean.setParentID(commentID);  // 부모댓글의 글번호를 세팅
        
        boolean result = commentDAO.insertComment(commentBean);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
        
        // 정상적으로 댓글을 저장했을경우 1을 전달한다.
        if(result) out.println("1");
        else out.println("0");
        
        out.close();
        
        return null;
    }

}
