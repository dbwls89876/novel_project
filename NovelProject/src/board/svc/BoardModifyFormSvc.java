package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardModifyFormSvc {
	public BoardBean getArticle(int boardID) {
		BoardBean article = null;
		Connection con = null;
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);

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
