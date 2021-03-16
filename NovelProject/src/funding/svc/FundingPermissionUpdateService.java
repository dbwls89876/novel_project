package funding.svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FundingDAO;

public class FundingPermissionUpdateService {
	public void PermissionUpdate(String[] fundingIDList) {
		Connection con = null;
		try {
			con = getConnection();
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			for(String fundingID : fundingIDList) {
				fundingDAO.updateFunding(fundingID);
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
