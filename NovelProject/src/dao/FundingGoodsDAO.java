package dao;

import java.sql.Connection;

import javax.sql.DataSource;

public class FundingGoodsDAO {
	DataSource ds;
	Connection con;
	private static FundingGoodsDAO fundingGoodsDAO;
	
	private FundingGoodsDAO() {}
	
	public static FundingGoodsDAO getInstance() {
		if(fundingGoodsDAO == null) {
			fundingGoodsDAO = new FundingGoodsDAO();
		}
		
		return fundingGoodsDAO;
		
	}
}
