package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	//funding 테이블 데이터 삽입
	public int insertArticle(Funding article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount=0;
		
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
			pstmt.setInt(1, article.getLiteraryID());
			pstmt.setInt(2, article.getFundingID());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getImage());
			pstmt.setInt(6, article.getTargetCost());
			pstmt.setInt(7, article.getNowCost());
			pstmt.setDate(8, article.getStartDate());
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardInsert : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
}
