package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;



public class NoticeDeleteProService {
	
	public boolean isArticleWriter(int noticeID, int id) throws Exception{
		boolean isArticleWriter = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		isArticleWriter = noticeDAO.isArticleNoticeWriter(noticeID, id);
		close(con);
		return isArticleWriter;
	}
	
	public boolean removeArticle(int noticeID) throws Exception{
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int deleteCount = noticeDAO.deleteArticle(noticeID);
		
		if(deleteCount > 0) {
			commit(con);
			isRemoveSuccess = true;
		}
		else {
			rollback(con);
		}
			close(con);
			return isRemoveSuccess;
		}
	}
