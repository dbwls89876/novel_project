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
	
	//funding 테이블 데이터 삽입
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
			sql="insert into funding (literaryID, fundingID, title, content, image, ";
			sql += "targetCost, nowCost, startDate, endDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, funding.getLiteraryID());
			pstmt.setInt(2, funding.getFundingID());
			pstmt.setString(3, funding.getTitle());
			pstmt.setString(4, funding.getContent());
			pstmt.setString(5, funding.getImage());
			pstmt.setInt(6, funding.getTargetCost());
			pstmt.setInt(7, funding.getNowCost());
			pstmt.setDate(8, funding.getStartDate());
			pstmt.setDate(9, funding.getEndDate());
			pstmt.execute();
			insertSucess = true;
		}catch(Exception e) {
			System.out.println("fundingInsert 오류 : " + e);
		}finally {
			close(pstmt);
		}
		return insertSucess;
	}
	
	//funding 테이블 한 줄의 정보 가져오기
	public Funding selectFunding(int literaryID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Funding funding = null;
		
		try {
			pstmt = con.prepareStatement("select * from funding where id = ?");
			pstmt.setInt(1,  literaryID);
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
				funding.setStartDate(rs.getDate("startDate"));
				funding.setEndDate(rs.getDate("endDate"));
			}
		}catch(Exception e) {
			System.out.println("fundingSelect 오류 : "  + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return funding;
	}

	//펀딩 테이블 리스트 가져오기
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
							,rs.getDate("startDate")
							,rs.getDate("endDate")
							,rs.getInt("permission")
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

	public void updateFunding(String fundingID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String sql = "update funding set permission = 1 where fundingID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fundingID);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
	}
}
