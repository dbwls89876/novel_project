package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeListService {

	public int getListCount() throws Exception {
		
		int listCount = 0;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		listCount = noticeDAO.selectListCount();
		close(con);
		return listCount;
	
	}

	public ArrayList<BoardBean> getArticleList(int page, int limit) {
		
		ArrayList<BoardBean>articleList = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		articleList = noticeDAO.selectArticleList(page, limit);
		close(con);
		return articleList;
		
	}

}
