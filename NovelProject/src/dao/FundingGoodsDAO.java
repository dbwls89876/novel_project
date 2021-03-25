package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.FundingGoods;

public class FundingGoodsDAO {
	DataSource ds;
	Connection con;
	private static FundingGoodsDAO fundingGoodsDAO;
	
	private FundingGoodsDAO() {}
	
	public static FundingGoodsDAO getInstance() {
		if(fundingGoodsDAO == null) {
			fundingGoodsDAO = new FundingGoodsDAO();
		}
		
		return fundingGoodsDAO;
		
	}

	public void setConnection(Connection con) {
		this.con = con;
		// TODO Auto-generated method stub
	}

	public ArrayList<FundingGoods> selectFundingGoodsList(int fundingID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FundingGoods> fundingGoodsList = null;
		String sql = "select * from fundingGoods where fundingID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fundingID);
			rs = pstmt.executeQuery();
			fundingGoodsList = new ArrayList<FundingGoods>();
			if(rs.next()) {
				do {
					fundingGoodsList.add(new FundingGoods(
							rs.getInt("fundingID")
							,rs.getInt("goodsID")
							,rs.getString("name")
							,rs.getInt("cost")
							,rs.getInt("count")
							,rs.getInt("maxNumber")
							));
				} while (rs.next());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return fundingGoodsList;
	}

	public FundingGoods selectFundingGoods(int goodsID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FundingGoods fundingGoods = null;
		String sql = "select * from fundingGoods where goodsID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fundingGoods = new FundingGoods();
				fundingGoods.setFundingID(rs.getInt("fundingID"));
				fundingGoods.setGoodsID(rs.getInt("goodsID"));
				fundingGoods.setName(rs.getString("name"));
				fundingGoods.setCost(rs.getInt("cost"));
				fundingGoods.setCount(rs.getInt("count"));
				fundingGoods.setMaxNumber(rs.getInt("maxNumber"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return fundingGoods;
	}

	public boolean insertFundingGoods(int fundingID, FundingGoods fundingGoods) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isRegisterSucess = false;
		String sql = "insert into fundingGoods (fundingID, name, cost, maxNumber) values (?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fundingID);
			pstmt.setString(2, fundingGoods.getName());
			pstmt.setInt(3, fundingGoods.getCost());
			pstmt.setInt(4, fundingGoods.getMaxNumber());
			pstmt.execute();
			isRegisterSucess = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return isRegisterSucess;
	}

	public boolean updateCount(int goodsID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isCountUpdateSucess = false;
		String sql = "update fundingGoods set count = count + 1 where goodsID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodsID);
			
			pstmt.execute();
			isCountUpdateSucess = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return isCountUpdateSucess;
	}
}
