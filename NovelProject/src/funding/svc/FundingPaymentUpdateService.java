package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CustomerDAO;
import dao.FundingDAO;
import dao.MemberDAO;

public class FundingPaymentUpdateService {
	
	public boolean paymentUpdate(int id, int goodsID, int fundingID, String memberID, int cost) {
		Connection con = null;
		boolean isInsertSucess = false;
		boolean isUpdateSucess = false;
		boolean isCostUpdateSucess = false;
		boolean commit = false;
		try {
			//구매자 정보 추가
			CustomerDAO customerDAO = CustomerDAO.getInstance();
			con = getConnection();
			customerDAO.setConnection(con);
			isInsertSucess = customerDAO.insertCustomer(id, goodsID);
			
			//구매한 펀딩상품의 비용을 펀딩에 더해줌
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			isUpdateSucess = fundingDAO.insertCost(fundingID, cost);
			
			//회원이 가진 돈을 출금
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			isCostUpdateSucess = memberDAO.updateCost(memberID, cost);
			if(isInsertSucess && isUpdateSucess && isCostUpdateSucess) {
				commit(con);
				commit = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rollback(con);
		}
		return commit;
	}
//	
//	//구매자 정보 추가
//	public boolean setCustomer(int id, int goodsID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		boolean isInsertSucess = false;
//		try {
//			CustomerDAO customerDAO = CustomerDAO.getInstance();
//			con = getConnection();
//			customerDAO.setConnection(con);
//			isInsertSucess = customerDAO.insertCustomer(id, goodsID);
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			close(con);
//		}
//		return isInsertSucess;
//	}
//	
//	//구매한 펀딩상품의 비용을 펀딩에 더해줌
//	public boolean updateFundingCost(int fundingID, int cost) {
//		Connection con = null;
//		boolean isUpdateSucess = false;
//		try {
//			FundingDAO fundingDAO = FundingDAO.getInstance();
//			con = getConnection();
//			fundingDAO.setConnection(con);
//			isUpdateSucess = fundingDAO.insertCost(fundingID, cost);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return isUpdateSucess;
//	}
//
//	public boolean updateMemberCost(String memberID, int cost) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		boolean isCostUpdateSucess = false;
//		try {
//			MemberDAO memberDAO = MemberDAO.getInstance();
//			con = getConnection();
//			memberDAO.setConnection(con);
//			isCostUpdateSucess = memberDAO.updateCost(memberID, cost);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return isCostUpdateSucess;
//	}

}
