package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import vo.CommentBean;

public class CommentDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static CommentDAO instance;
	
	private CommentDAO() {}
	public static CommentDAO getInstance() {
		if(instance == null)
			instance = new CommentDAO();
		return instance;
	}

	public int getSeq() {
		
		int result = 1;
		try {
			con = DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select comment_seq.nextval from dual");
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = rs.getInt(1);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;
	}
	
	public boolean insertComment(CommentBean comment) {
		
		boolean result = false;
		
		try {
			con = DBConnection.getConnection();
			
			con.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into boardComment (commentID, boardID, memberID, date, parent, content) values(?, ?, ?, now(), ?, ?)");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, comment.getCommentID());
			pstmt.setInt(2, comment.getBoardID());
			pstmt.setString(3, comment.getMemberID());
			pstmt.setInt(4, comment.getParentID());
			pstmt.setString(5, comment.getContent());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = true;
				con.commit();
			}
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			System.out.println("insertComment 에러 : " + e);
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		close();
		return result;
	}
	
	public ArrayList<CommentBean> getCommentList(int boardNum){
        ArrayList<CommentBean> list = new ArrayList<CommentBean>();
        
        try {
            con = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select level, commentID, boardID, memberID, date, parentID, content");
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, boardNum);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                CommentBean comment = new CommentBean();
                comment.setCommentLevel(rs.getInt("commentLevel"));
                comment.setCommentID(rs.getInt("commentID"));
                comment.setBoardID(rs.getInt("boardID"));
                comment.setMemberID(rs.getString("memberID"));
                comment.setDate(rs.getDate("date"));
                comment.setParentID(rs.getInt("parentID"));
                comment.setContent(rs.getString("content"));
                list.add(comment);
            }
                
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return list;
    }
    
    private void close()
    {
        try {
            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( con != null ){ con.close(); con=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } // end close()    
        
}
