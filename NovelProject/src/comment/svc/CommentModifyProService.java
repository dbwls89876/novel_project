package comment.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CommentDAO;
import vo.CommentBean;

public class CommentModifyProService {

	public boolean isArticleWriter(int commentID, String memberID) throws Exception{

		boolean isArticleWriter = false;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		isArticleWriter = commentDAO.isArticleBoardWriter(commentID, memberID);
		close(con);
		return isArticleWriter;
	}

	public static boolean modifyArticle(CommentBean article) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		int updateCount = commentDAO.updateArticle(article);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);		
		return isModifySuccess;
	}

}
