package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import vo.Member;

public class MyInformationViewService {

	public Member getMember(String memberID) {
		Member member = null;
		Connection con = null;
		try {
			con = getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			member = memberDAO.selectMember(memberID);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return member;
		}
	}