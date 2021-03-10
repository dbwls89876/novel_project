package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Member;

public class MemberDAO {
	DataSource ds;
	Connection con;
	private static MemberDAO memberDAO;
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int insertMember(Member member) {
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount=0;
		
		try {
			sql="insert into member value(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getMobile());
			pstmt.setString(6, member.getAddress());
			pstmt.setInt(7, member.getGrade());
			pstmt.setInt(8, member.getLevel());
			pstmt.setDate(9, (Date) member.getDate());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberInsert 에러 : " + e);
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}

	public Member selectMember(int id) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setId(id);
				member.setMemberID(rs.getString("memberID"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setMobile(rs.getString("mobile"));
				member.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			pstmt = con.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<Member> selectMemberList(int page, int limit) {
		ArrayList<Member> memberList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		String sql = "select * from " + 
		"(select rownum rnum, a.* from (select * from member) a) where rnum between ? and ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberList = new ArrayList<Member>();
				do {
					Member member = new Member();
					member.setId(rs.getInt("id"));
					member.setMemberID(rs.getString("memberID"));
					member.setPassword(rs.getString("password"));
					member.setName(rs.getString("name"));
					member.setNickname(rs.getString("nickname"));
					member.setMobile(rs.getString("mobile"));
					member.setAddress(rs.getString("address"));
				}while(rs.next());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberList;
	}

	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set memberID=?, password = ?, name = ?, nickname = ?,"+
		" mobile = ?, address = ? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getMobile());
			pstmt.setString(6, member.getAddress());
			pstmt.setInt(7, member.getGrade());
			pstmt.setInt(8, member.getLevel());
			pstmt.setDate(9, (Date) member.getDate());
			pstmt.setInt(10, member.getId());
			updateCount = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteMember(String id) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
}