package literary.svc;

import vo.Literary;
import static db.JdbcUtil.*;
import dao.LiteraryDAO;
import java.sql.Connection;

public class LiteraryRegistService {

	public boolean registArticle(Literary literary) {
		boolean isWriteSuccess = false;
		Connection con = null;
		try{
			con = getConnection();
			LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
			literaryDAO.setConnection(con);
			int insertCount = literaryDAO.insertArticle(literary);
		
		if(insertCount > 0) {
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
