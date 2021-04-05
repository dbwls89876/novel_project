package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;

public class CashChargeService {

	public void getMoney(String memberID, int cash) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			MemberDAO memberDAO = MemberDAO.getInstance();
			con = getConnection();
			memberDAO.setConnection(con);
			memberDAO.addMoney(memberID, cash);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
	}

}
