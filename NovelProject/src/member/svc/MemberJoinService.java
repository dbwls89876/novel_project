package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import vo.Member;

public class MemberJoinService {
	public boolean joinMember(Member member) {
		boolean isJoinSuccess = false;
		Connection con =null;
		try {
			con = getConnection();
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			
			int insertCount = memberDAO.insertMember(member);
			
			if(insertCount > 0) {
				commit(con);
				isJoinSuccess = true;
			}else {
				rollback(con);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con);
		}
		return isJoinSuccess;
	}


}
