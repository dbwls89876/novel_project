package comment.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommentDAO;
import vo.CommentBean;

public class CommentListService {

	public int getCommentListCount() throws Exception{

		int commentListCount = 0;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		close(con);
		return commentListCount;
	}

	public ArrayList<CommentBean> getCommentArticle(int commentPage, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
