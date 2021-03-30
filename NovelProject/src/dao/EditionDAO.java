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
			sql="insert into edition (id,literaryID,editionID,title,content,count,date) values (?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, edition.getId());
			pstmt.setInt(2, edition.getLiteraryID());
			pstmt.setInt(3, edition.getEditionID());
			pstmt.setString(45, edition.getTitle());
			pstmt.setString(5, edition.getContent());
			pstmt.setInt(6, edition.getCount());
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
	public int updateCount(int num) {
		int updateCount=0;
		PreparedStatement pstmt = null;
		String sql = "update edition set count = count+1 where num=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	//회차 상세 내용 보기
	public Edition selectArticle(int num) {
		Edition article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from edition where num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				article = new Edition();
				article.setId(rs.getInt("id"));
				article.setLiteraryID(rs.getInt("literaryID"));
				article.setEditionID(rs.getInt("editionID"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setDate(rs.getDate("date"));
				article.setCount(rs.getInt("count"));
				article.setNum(rs.getInt("num"));				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return article;
	}
}
