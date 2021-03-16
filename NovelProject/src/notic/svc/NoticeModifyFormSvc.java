package notic.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;

public class NoticeModifyFormSvc {
	public BoardBean getArticle(int noticeID) {
		BoardBean article = null;
		Connection con = null;
		try {
			con = getConnection();
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);

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
