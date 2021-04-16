package edition.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.EditionDAO;
import dao.NoticeDAO;
import vo.Edition;

public class EditionListService {
	public int getListCount() throws Exception {
		int listCount = 0;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		listCount = noticeDAO.selectListCount();
		close(con);
		return listCount;
	}
	public ArrayList<Edition> getArticleList(String literaryID, int page, int limit) {
		ArrayList<Edition> articleList = null;
		Connection con = getConnection();
		EditionDAO editionDAO = EditionDAO.getInstance();
		editionDAO.setConnection(con);
		articleList = editionDAO.selectArticleList(literaryID, page, limit);
			close(con);
		return articleList;
	}
}