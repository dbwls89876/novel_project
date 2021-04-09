package edition.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.LiteraryDAO;
import vo.Literary;

public class EditionRegistFormService {

	public ArrayList<Literary> findId(int literaryID) {
		Connection con = null;
		ArrayList<Literary> artistLiteraryList = null;
		try {
			LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
			con = getConnection();
			literaryDAO.setConnection(con);
			artistLiteraryList = literaryDAO.selectLiteraryList(literaryID);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(con);
		}
		
		return artistLiteraryList;
	}
}
