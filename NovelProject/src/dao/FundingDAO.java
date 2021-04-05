package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Funding;


public class FundingDAO {
	DataSource ds;
	Connection con;
	private static FundingDAO fundingDAO;
	
	private FundingDAO() {}
	
	public static FundingDAO getInstance() {
		if(fundingDAO == null) {
			fundingDAO = new FundingDAO();
		}
		
		return fundingDAO;
		
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//펀딩 등록하기
	public boolean insertFunding(Funding funding) {
		PreparedStatement pstmt = null;
		String sql = "";
		boolean insertSucess = false;
		
		try {
			/* auto
			 * pstmt = con.prepareStatement("select max(board_num) from board"); rs =
			 * pstmt.executeQuery();
			 * 
			 * if(rs.next()) num = rs.getInt(1)+1; else num=1;
			 */
			sql="insert into funding (literaryID, title, content, image, ";
			sql += "targetCost, startDate, endDate, deliveryDate) values (?, ?, ?, ?, ?, now(), ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, funding.getLiteraryID());
			pstmt.setString(2, funding.getTitle());
			pstmt.setString(3, funding.getContent());
			pstmt.setString(4, funding.getImage());
			pstmt.setInt(5, funding.getTargetCost());
			pstmt.setDate(6, funding.getEndDate());
			pstmt.setDate(7, funding.getDeliveryDate());
			pstmt.execute();
			insertSucess = true;
		}catch(Exception e) {
			System.out.println("fundingInsert 오류 : " + e);
		}finally {
			close(pstmt);
		}
		return insertSucess;
	}
	
	//펀딩 불러오기
	public Funding selectFunding(int fundingID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Funding funding = null;
		
		try {
			pstmt = con.prepareStatement("select * from funding where fundingID = ?");
			pstmt.setInt(1,  fundingID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				funding = new Funding();
				funding.setLiteraryID(rs.getInt("literaryID"));
				funding.setFundingID(rs.getInt("fundingID"));
				funding.setTitle(rs.getString("title"));
				funding.setContent(rs.getString("content"));
				funding.setImage(rs.getString("image"));
				funding.setTargetCost(rs.getInt("targetCost"));
				funding.setNowCost(rs.getInt("nowCost"));
				funding.setPermission(rs.getInt("permission"));
				funding.setStartDate(rs.getDate("startDate"));
				funding.setEndDate(rs.getDate("endDate"));
				funding.setDeliveryDate(rs.getDate("deliveryDate"));
			}
		}catch(Exception e) {
			System.out.println("fundingSelect 오류 : "  + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return funding;
	}

	//�ݵ� ���̺� ����Ʈ ��������
	public ArrayList<Funding> selectFundingList() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Funding> fundingList = null;
		String sql = "select * from funding";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			fundingList = new ArrayList<Funding>();
			if(rs.next()) {
				do {
					fundingList.add(new Funding(
							rs.getInt("literaryID")
							,rs.getInt("fundingID")
							,rs.getString("title")
							,rs.getString("content")
							,rs.getString("image")
							,rs.getInt("targetCost")
							,rs.getInt("nowCost")
							,rs.getInt("permission")
							,rs.getDate("startDate")
							,rs.getDate("endDate")
							,rs.getDate("deliveryDate")
							));
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return fundingList;
	}

	public void updateFunding(String title) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String sql = "update funding set permission = 1 where title=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
	}

	public boolean insertCost(int fundingID, int cost) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isUpdateSucess = false;
		String sql = "update funding set nowCost=nowCost+? where fundingID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cost);
			pstmt.setInt(2, fundingID);
			pstmt.executeUpdate();
			isUpdateSucess = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return isUpdateSucess;
	}

	public int selectFundingID(Funding funding) {
		// TODO Auto-generated method stub
		int fundingID = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select fundingID from funding where title=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, funding.getTitle());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fundingID = rs.getInt("fundingID");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return fundingID;
	}

	public ArrayList<Funding> selectRecentFunding() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Funding> fundingList = null;
		
		try {
			pstmt = con.prepareStatement("select * from funding where permission=1 order by fundingID desc limit 5");
			rs = pstmt.executeQuery();
			
			fundingList = new ArrayList<Funding>();
			if(rs.next()) {
				do {
					fundingList.add(new Funding(
							rs.getInt("literaryID")
							,rs.getInt("fundingID")
							,rs.getString("title")
							,rs.getString("content")
							,rs.getString("image")
							,rs.getInt("targetCost")
							,rs.getInt("nowCost")
							,rs.getInt("permission")
							,rs.getDate("startDate")
							,rs.getDate("endDate")
							,rs.getDate("deliveryDate")
							));
				} while (rs.next());
			}
		}catch(Exception e) {
			System.out.println("fundingRecentSelect 오류 : "  + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return fundingList;
	}
}
