package member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MyInformationModifyService {

	public boolean getMember(Member member) {
		boolean isModifySuccess = false;
		Connection con =null;
		try {
			con = getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			int insertCount = memberDAO.insertMember(member);
			
			if(insertCount > 0) {
				commit(con);
				isModifySuccess = true;
			}else {
				rollback(con);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isModifySuccess;
	}


}