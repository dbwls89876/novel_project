package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;



public class NoticeDeleteProService {
	
	public boolean removeArticle(int noticeID) {
		boolean isDeleteSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			
			int deleteCount = noticeDAO.deleteArticle(noticeID);
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
