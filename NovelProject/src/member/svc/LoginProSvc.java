package member.svc;

import static db.JdbcUtil.*;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class LoginProSvc {

	public Member getMember(String memberID) {
		Member member = null;
		Connection con = null;
		try {
			con = getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			member = memberDAO.selectMember(memberID);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(con);
		}
		return member;
	}

}
