package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import vo.Member;

public class MyInformationViewService {

	public Member getMember(String viewId) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		Member member = memberDAO.selectMember(viewId);
		close(con);
		return member;
	}
}
