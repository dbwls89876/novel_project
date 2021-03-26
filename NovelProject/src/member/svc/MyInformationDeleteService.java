package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MyInformationDeleteService {

	public boolean deleteMember(String memberID) {
		boolean isDeleteSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			int updateCount = memberDAO.deleteMember(memberID);
			if(updateCount > 0) {
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