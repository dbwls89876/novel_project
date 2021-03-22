package board.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;



public class BoardDeleteProService {
	
	public boolean isArticleWriter(int boardID, int id) throws Exception{
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		isArticleWriter = boardDAO.isArticleBoardWriter(boardID, id);
		close(con);
		return isArticleWriter;
	}
	
	
	public boolean removeArticle(int boardID) throws Exception{
		
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int deleteCount = boardDAO.deleteArticle(boardID);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}

}