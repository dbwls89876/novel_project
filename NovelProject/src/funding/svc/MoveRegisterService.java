package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.LiteraryDAO;
import vo.Literary;

public class MoveRegisterService {
	
	//�ݵ� ����� ���� �۰��� ��ǰ ���� �ҷ�����
	public ArrayList<Literary> findTitle(int id) {
		Connection con = null;
		ArrayList<Literary> artistLiteraryList = null;
		try {
			LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
			con = getConnection();
			literaryDAO.setConnection(con);
			artistLiteraryList = literaryDAO.selectArtistLiteraryList(id);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(con);
		}
		
		return artistLiteraryList;
	}
}
