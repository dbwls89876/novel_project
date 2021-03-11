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
							,rs.getString("name")
							,rs.getString("content")
							,rs.getString("genre")
							,rs.getDouble("score")));
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
	
}
