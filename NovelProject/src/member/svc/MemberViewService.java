package member.svc;

import vo.Member;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.MemberDAO;

public class MemberViewService {

	public Member getMember(String viewId) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		Member member = memberDAO.selectMember(viewId);
		close(con);
		return member;
	}



}
