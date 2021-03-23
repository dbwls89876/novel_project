package edition.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.EditionDAO;
import vo.Edition;

public class EditionWriterListService {
	public int getListCount() {
		int listCount = 0;
		Connection con = null;
		try {
			con = getConnection();
			EditionDAO editionDAO = EditionDAO.getInstance();
			editionDAO.setConnection(con);
			listCount = editionDAO.selectListCount();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return listCount;
	}

	public ArrayList<Edition> getArticleList(int page, int limit) {
		ArrayList<Edition> list = null;
		Connection con = null;
		try {
			con = getConnection();
			EditionDAO editionDAO = EditionDAO.getInstance();
			editionDAO.setConnection(con);
			list = editionDAO.selectArticleList(page, limit);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return list;
	}

}
