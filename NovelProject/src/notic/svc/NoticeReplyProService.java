package notic.svc;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;

public class NoticeReplyProService {

	public boolean replyArticle(BoardBean article) {
		boolean isReplySuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int insertCount = noticeDAO.insertReplyArticle(article);
			
			if(insertCount > 0) {
				commit(con);
				isReplySuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isReplySuccess;
	}

}
