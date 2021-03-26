package member.svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;

public class MyInformationDeleteService {

	public boolean deleteMember(String id) {
		boolean isDeleteSuccess = false;
		Connection con = null;
		try {
			con=getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			int deleteCount = memberDAO.deleteMember(id);
			if(deleteCount>0) {
				commit(con);
				isDeleteSuccess = true;
			}else {
				rollback(con);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return isDeleteSuccess;
	}

}
