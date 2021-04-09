package edition.svc;

import vo.Edition;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.EditionDAO;

public class EditionRegistService {

	public boolean registEdition(Edition edition) {
		boolean isWriteSuccess = false;
		Connection con = null;
		try{
			con = getConnection();
			EditionDAO editionDAO = EditionDAO.getInstance();
			editionDAO.setConnection(con);
			int insertCount = editionDAO.insertEdition(edition);
		
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