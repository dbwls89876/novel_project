package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeDetailService {

	public BoardBean getArticle(int noticeID) {
		BoardBean article = null;
		Connection con = null;
		
		
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int updateCount = noticeDAO.updateReadCount(noticeID);
			
			if(updateCount > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			
			article = noticeDAO.selectArticle(noticeID);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return article;
	}

}
