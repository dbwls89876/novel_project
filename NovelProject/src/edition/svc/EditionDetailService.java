package edition.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.EditionDAO;
import vo.Edition;

public class EditionDetailService {

	public Edition getArticle(int num) {
		Edition edition = null;
		Connection con = null;
		try {
			con=getConnection();
			EditionDAO editionDAO = EditionDAO.getInstance();
			editionDAO.setConnection(con);
			
			int updateCount = editionDAO.updateCount(num);
			if(updateCount>0) {
				commit(con);
			}else {
				rollback(con);
			}
			
			edition = editionDAO.selectArticle(num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(con);
		}
		return edition;
	}

}
