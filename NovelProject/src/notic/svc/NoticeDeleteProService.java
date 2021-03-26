package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;



public class NoticeDeleteProService {
	
	public boolean isArticleWriter(int noticeID, String memberID) throws Exception{
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isArticleWriter = noticeDAO.isArticleNoticeWriter(noticeID, memberID);
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
