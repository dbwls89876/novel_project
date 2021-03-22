package funding.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.FundingDAO;

public class FundingPermissionUpdateService {
	public void PermissionUpdate(String[] fundingList) {
		Connection con = null;
		try {
			con = getConnection();
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			for(String funding : fundingList) {
				fundingDAO.updateFunding(funding);
			}
			commit(con);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
	}
}
