package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;
import dao.FundingGoodsDAO;
import vo.Funding;
import vo.FundingGoods;

public class FundingContentService {

	public Funding getFunding(int fundingID) {
		Connection con = null;
		Funding funding = null;
		try {
			con = getConnection();
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			funding = fundingDAO.selectFunding(fundingID);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return funding;
	}
	
	public ArrayList<FundingGoods> getFundingGoodsList(int fundingID) {
		// TODO Auto-generated method stub
		Connection con = null;
		ArrayList<FundingGoods> fundingGoodsList = null;
		try {
			con = getConnection();
			FundingGoodsDAO fundingGoodsDAO = FundingGoodsDAO.getInstance();
			fundingGoodsDAO.setConnection(con);
			fundingGoodsList = fundingGoodsDAO.selectFundingGoodsList(fundingID);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fundingGoodsList;
	}

}
