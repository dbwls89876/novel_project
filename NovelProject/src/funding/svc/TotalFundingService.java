package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;
import vo.Funding;

public class TotalFundingService {
	public ArrayList<Funding> getFundingList(){
		System.out.println("actioncheck1");
		Connection con = null;
		ArrayList<Funding> fundingList = null;
		try {
			FundingDAO fundingDAO = FundingDAO.getInstance();
			con = getConnection();
			fundingDAO.setConnection(con);
			fundingList = fundingDAO.selectFundingList();
			System.out.println("servicecheck2");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(con);
		}
		return fundingList;
	}
}
