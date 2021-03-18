package board.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardDetailService {

	public BoardBean getArticle(int boardID) throws Exception{
		BoardBean article = null;
		Connection con = null;
		
		
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			int updateCount = boardDAO.updateReadCount(boardID);
			
			if(updateCount > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			
			article = boardDAO.selectArticle(boardID);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return article;
	}

}
