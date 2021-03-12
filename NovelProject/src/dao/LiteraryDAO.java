package dao;

import java.sql.*;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import vo.Literary;

public class LiteraryDAO {
	Connection con;
	private static LiteraryDAO boardDAO;
	
	private LiteraryDAO() {
		
	}
	
	public static LiteraryDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new LiteraryDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
		
	}

	public ArrayList<Literary> selectLiteraryList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Literary> literaryList = null;
		
		try {
			pstmt = con.prepareStatement("select * from literary");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				literaryList = new ArrayList<Literary>();
				
				do {
					literaryList.add(new Literary(
							rs.getInt("id")
							,rs.getInt("literaryID")
							,rs.getString("title")
							,rs.getString("content")
							,rs.getString("genre")
							,rs.getDouble("score")
							,rs.getString("image")
							,rs.getDate("date")));
				} while (rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return literaryList;
	}

	public ArrayList<Literary> selectArtistLiteraryList(int id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Literary> artistLiteraryList = null;
		String sql = "select * from literary where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				artistLiteraryList = new ArrayList<Literary>();
				do {
					artistLiteraryList.add(new Literary(
							rs.getInt("id")
							,rs.getInt("literaryID")
							,rs.getString("title")
							,rs.getString("content")
							,rs.getString("genre")
							,rs.getDouble("score")
							,rs.getString("image")
							,rs.getDate("date")));
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return artistLiteraryList;
	}
	
}
