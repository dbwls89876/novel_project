package funding.svc;

import java.sql.Connection;

import dao.FundingDAO;
import vo.Funding;

import static db.JdbcUtil.*;

public class FundingRegisterService {
	public boolean registerFunding(Funding funding) {
		boolean isRegisterSuccess = false;
		Connection con = null;
		try {
			con = getConnection();
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			isRegisterSuccess = fundingDAO.insertFunding(funding);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isRegisterSuccess;
	}
}
