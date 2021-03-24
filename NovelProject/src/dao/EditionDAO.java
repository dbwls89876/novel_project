package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;
import vo.Edition;

public class EditionDAO {
	DataSource ds;
	Connection con;
	private static EditionDAO editionDAO;
	
	private EditionDAO() {
		
	}
	
	public static EditionDAO getInstance() {
		if(editionDAO == null) {
			editionDAO = new EditionDAO();
		}
		return editionDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//글등록
	public int insertArticle(Edition edition) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="";
		int insertCount=0;
		
		try {
			sql="insert into edition (id,title,content,date,count) values (?,?,?,now(),?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, edition.getId());
			pstmt.setString(2, edition.getTitle());
			pstmt.setString(3, edition.getContent());
			pstmt.setInt(4, edition.getCount());
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("editionRegist 에러 : "+e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}

	//글 갯수 구하기
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select count(*) from edition");
			rs = pstmt.executeQuery();
			
			if(rs.next()) { listCount = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getListCount 에러: " +e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	//글 목록보기
	public ArrayList<Edition> selectArticleList(int page, int limit) {
		ArrayList<Edition> list = new ArrayList<Edition>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from edition order by editionID desc limit ?, ?";
		Edition edition = new Edition();
		int startrow = (page-1)*limit;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				edition.setId(rs.getInt("id"));
				edition.setLiteraryID(rs.getInt("literaryID"));
				edition.setEditionID(rs.getInt("editionID"));
				edition.setTitle(rs.getString("title"));
				edition.setContent(rs.getString("content"));
				edition.setDate(rs.getDate("date"));
				edition.setCount(rs.getInt("count"));
				list.add(edition);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	

	//조회수 업데이트
	public int updateReadCount(int board_num) {
		int updateCount=0;
		PreparedStatement pstmt = null;
		String sql = "update board set board_readcount = board_readcount+1 where board_num=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public Edition selectEdition(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
