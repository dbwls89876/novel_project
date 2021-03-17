package funding.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.FundingDAO;
import dao.MemberDAO;
import vo.Funding;
import vo.Member;

public class FundingPaymentService {

	public Funding getFunding(int literaryID) {
		// TODO Auto-generated method stub
		Connection con = null;
		Funding funding = null;
		try {
			FundingDAO fundingDAO = FundingDAO.getInstance();
			con = getConnection();
			fundingDAO.setConnection(con);
			funding = fundingDAO.selectFunding(literaryID);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return funding;
	}
	
	public Member getMember(int id) {
		Connection con = null;
		Member member = null;
		try {
			MemberDAO memberDAO = MemberDAO.getInstance();
			con = getConnection();
			memberDAO.setConnection(con);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		return member;
	}
	
}
