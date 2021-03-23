package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeDetailService {

	public BoardBean getArticle(int noticeID) {
		
		BoardBean article = null;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateReadCount(noticeID);
			
		if(updateCount > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
			
		article = noticeDAO.selectArticle(noticeID);
		close(con);
		return article;
		
	}

}
