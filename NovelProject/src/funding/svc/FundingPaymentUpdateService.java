package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CustomerDAO;
import dao.FundingDAO;

public class FundingPaymentUpdateService {

	//구매자 정보 추가
	public boolean setCustomer(int id, int goodsID) {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean isInsertSucess = false;
		try {
			CustomerDAO customerDAO = CustomerDAO.getInstance();
			con = getConnection();
			customerDAO.setConnection(con);
			isInsertSucess = customerDAO.insertCustomer(id, goodsID);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isInsertSucess;
	}
	
	//구매한 펀딩상품의 비용을 펀딩에 더해줌
	public boolean updateFundingCost(int fundingID, int cost) {
		Connection con = null;
		boolean isUpdateSucess = false;
		try {
			FundingDAO fundingDAO = FundingDAO.getInstance();
			con = getConnection();
			fundingDAO.setConnection(con);
			isUpdateSucess = fundingDAO.insertCost(fundingID, cost);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isUpdateSucess;
	}

}
