package funding.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.CustomerDAO;

public class FundingPaymentUpdateService {

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

}
