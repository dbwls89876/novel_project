package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;

public class CashChargeService {

	public void getMoney(String memberID, int cash) {
		// TODO Auto-generated method stub
		Connection con = null;
		boolean isMoneyUpdateSucess = false;
		try {
			MemberDAO memberDAO = MemberDAO.getInstance();
			con = getConnection();
			memberDAO.setConnection(con);
			isMoneyUpdateSucess = memberDAO.addMoney(memberID, cash);
			if(isMoneyUpdateSucess)
				commit(con);
		}catch (Exception e) {
			// TODO: handle exception
			rollback(con);
			e.printStackTrace();
		}finally {
			close(con);
		}
	}
}
