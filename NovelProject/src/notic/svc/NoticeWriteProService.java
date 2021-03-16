package notic.svc;

import java.sql.Connection;

import dao.NoticeDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;

public class NoticeWriteProService {
	public boolean registArticle(BoardBean boardBean) {
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		try {
			NoticeDAO noticeDAO = NoticeDAO.getInstance();
			noticeDAO.setConnection(con);
			int insertCount = noticeDAO.insertArticle(boardBean);
			
			if(insertCount>0) {
				commit(con);
				isWriteSuccess = true;
			}
			else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		
		return isWriteSuccess;
	}
}
