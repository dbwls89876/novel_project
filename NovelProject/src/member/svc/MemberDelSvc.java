package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class MemberDelSvc {
		public boolean deleteMember(String id) {
			boolean isDeleteSuccess = false;
			Connection con = null;
			try {
				con = getConnection();
				MemberDAO memberDAO = MemberDAO.getInstance();
				memberDAO.setConnection(con);	
				int deleteCount = memberDAO.deleteMember(id);
				if(deleteCount > 0) {
					commit(con);
					isDeleteSuccess = true;
				}else {
					rollback(con);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				close(con);
			}
			return isDeleteSuccess;
			
		}
	
	
}
