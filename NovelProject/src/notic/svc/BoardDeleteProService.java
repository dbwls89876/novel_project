package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;



public class BoardDeleteProService {
	
	
	
	public boolean removeArticle(int board_num) {
		boolean isDeleteSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			
			int deleteCount = boardDAO.deleteArticle(board_num);
			if(deleteCount > 0) {
				commit(con);
				isDeleteSuccess = true;
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return true;
	}

	public boolean isArticleWriter() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
