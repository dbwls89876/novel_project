package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeModifyProService {

	public boolean isArticleWriter(int noticeID, String memberID) throws Exception {
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		isArticleWriter = noticeDAO.isArticleNoticeWriter(noticeID, memberID);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(BoardBean article) throws Exception{
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		NoticeDAO noticeDAO = NoticeDAO.getInstance();
		noticeDAO.setConnection(con);
		int updateCount = noticeDAO.updateArticle(article);
			
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}

}
