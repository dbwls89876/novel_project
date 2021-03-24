package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.LiteraryDAO;
import vo.Literary;

public class MyLiteraryListService {

	public ArrayList<Literary> getLiteraryList() {
		ArrayList<Literary> myLiteraryList = null;
		LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
		Connection con = null;
		try {
			con = getConnection();
			literaryDAO.setConnection(con);
			myLiteraryList = literaryDAO.selectLiteraryList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return myLiteraryList;
	}
}
