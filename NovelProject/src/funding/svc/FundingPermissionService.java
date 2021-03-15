package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;
import vo.Funding;

public class FundingPermissionService {
	public ArrayList<Funding> permissionWaitingList(){
		ArrayList<Funding> fundingList = null;
		
		try {
			Connection con = getConnection();
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			fundingList = fundingDAO.selectFundingList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fundingList;
	}
	
}
