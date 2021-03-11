package board.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardModifyProService {

	public boolean isArticleWriter(int boardID, int id) {
		boolean isArticleWriter = false;
		Connection con = null;
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			isArticleWriter = boardDAO.isArticleBoardWriter(boardID,id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isArticleWriter;
	}

	public boolean modifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			int updateCount = boardDAO.deleteArticle(article);
			if(updateCount > 0) {
				commit(con);
				isModifySuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(con);
		}
		return isModifySuccess;
	}

}
