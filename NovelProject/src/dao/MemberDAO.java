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
			sql="insert into member (memberID,password,name,nickname,mobile,postCode,roadAddress,detailAddress,date) value(?,?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getMobile());
			pstmt.setInt(6, member.getPostCode());
			pstmt.setString(7, member.getRoadAddress());
			pstmt.setString(8, member.getDetailAddress());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("memberInsert 오류 : " + e);
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public boolean idCheck(String memberID) {
		boolean check = false;
		PreparedStatement pstmt = null;

		String sql = "select * from member where memberID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			check = pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("memberSelect 오류 : " + e);
		}finally {
			close(pstmt);
		}
		
		return check;
	}
	
	public Member selectMember(String memberID) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where memberID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getInt("id"));
				member.setMemberID(rs.getString("memberID"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setMobile(rs.getString("mobile"));
				member.setPostCode(Integer.parseInt(rs.getString("postCode")));
				member.setRoadAddress(rs.getString("roadAddress"));
				member.setDetailAddress(rs.getString("detailAddress"));
				member.setMoney(rs.getInt("money"));;
			}
		} catch (Exception e) {
			System.out.println("memberSelect 오류 : " + e);
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
			System.out.println("getListCount 오류 : " + e);
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
					member.setPostCode(Integer.parseInt(rs.getString("postCode")));
					member.setRoadAddress(rs.getString("roadAddress"));
					member.setDetailAddress(rs.getString("detailAddress"));
				}while(rs.next());
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("memberList 오류 : " + e);
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
		String sql = "update member set password = ?, mobile = ?, postCode = ?, roadAddress=?, detailAddress=? where memberID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMobile());
			pstmt.setInt(3, member.getPostCode());
			pstmt.setString(4, member.getRoadAddress());
			pstmt.setString(5, member.getDetailAddress());
			pstmt.setString(6, member.getMemberID());
			updateCount = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteMember(String memberID) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from member where memberID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberID);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public boolean updateCost(String memberID, int cost) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isCostUpdateSucess = false;
		String sql = "update member set money=money-? where memberID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cost);
			pstmt.setString(2, memberID);
			pstmt.executeUpdate();
			isCostUpdateSucess = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return isCostUpdateSucess;
	}

	public boolean addMoney(String memberID, int money) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		boolean isMoneyUpdateSucess = false;
		String sql = "update member set money=money+? where memberID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, memberID);
			pstmt.executeUpdate();
			isMoneyUpdateSucess = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return isMoneyUpdateSucess;
	}	
}
