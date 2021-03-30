package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import vo.CommentBean;

public class CommentDAO {
	
	DataSource ds;
	Connection con;
	private static CommentDAO commentDAO;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		if (commentDAO == null) {
			commentDAO = new CommentDAO();
		} 
		return commentDAO;
	}
	
	//댓글 등록
	public int insertComment (CommentBean commentArticle) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;
		
		try {
			rs = pstmt.executeQuery();
			
			sql = "insert into boardComment (commentID, memberID, content, date) VALUES (?, ?, ?, now())";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, commentArticle.getMemberID());
			pstmt.setString(3, commentArticle.getContent());
			pstmt.setInt(4, 0);
			
			insertCount = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			System.out.println("insertCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//댓글 수정
	public int updateComment (CommentBean commentArticle) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update boardComment set content = ? where commentID = ?"; 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commentArticle.getContent());
			pstmt.setInt(2, commentArticle.getCommentID());
			updateCount = pstmt.executeUpdate();		
		} catch(Exception e) {
			System.out.println("updateCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//댓글 삭제
	public int deleteComment (int commentID) {
		
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from board where commentID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentID);
			deleteCount = pstmt.executeUpdate();			
		} catch(Exception e) {
			System.out.println("deleteCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}
}

