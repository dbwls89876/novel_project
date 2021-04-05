package action;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;

import static db.JdbcUtil.*;
import vo.Funding;

public class mainService {
	public ArrayList<Funding> getRecentFunding() {
		Connection con = null;
		ArrayList<Funding> fundingList = null;
		
		try {
			FundingDAO fundingDAO = FundingDAO.getInstance();
			con = getConnection();
			fundingDAO.setConnection(con);
			fundingList = fundingDAO.selectRecentFunding();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return fundingList;
	}
}
