package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

public class NoticeListService {

	public int getListCount() {
		int listCount = 0;
		Connection con = null;
		try {
			con = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			listCount = boardDAO.selectListCount();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return listCount;
	}

	public ArrayList<BoardBean> getArticleList(int page, int limit) {
		ArrayList<BoardBean>articleList = null;
		Connection con = getConnection();
		try {
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(con);
			articleList = boardDAO.selectArticleList(page, limit);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return articleList;
	}

}
