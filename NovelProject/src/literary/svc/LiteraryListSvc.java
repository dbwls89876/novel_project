package literary.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.LiteraryDAO;
import vo.Literary;

public class LiteraryListSvc {

	public ArrayList<Literary> getLiteraryList() {
		ArrayList<Literary> literaryList = null;
		LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
		Connection con = null;
		try {
			con = getConnection();
			literaryDAO.setConnection(con);
			literaryList = literaryDAO.selectLiteraryList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return literaryList;
	}

}
