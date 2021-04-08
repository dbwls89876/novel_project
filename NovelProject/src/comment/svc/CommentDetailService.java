package comment.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CommentDAO;
import vo.CommentBean;

public class CommentDetailService {

	public CommentBean getArticle(int commentID) throws Exception{
		
		CommentBean article = null;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		
		article = commentDAO.selectArticle(commentID);
		close(con);
		return article;
	}

}
