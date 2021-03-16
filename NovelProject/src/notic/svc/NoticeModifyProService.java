package notic.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeModifyProService {

	public boolean isArticleWriter(int noticeID, int id) {
		boolean isArticleWriter = false;
		Connection con = null;
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			isArticleWriter = noticeDAO.isArticleBoardWriter(noticeID,id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isArticleWriter;
	}

	public boolean modifyArticle(BoardBean article) {
		boolean isModifySuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int updateCount = noticeDAO.deleteArticle(article);
			if(updateCount > 0) {
				commit(con);
				isModifySuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(con);
		}
		return isModifySuccess;
	}

}
