package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import vo.CommentBean;

public class CommentDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static CommentDAO commentDAO;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO();
		}
		return commentDAO;
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
	
	//댓글 등록
	public boolean insertComment(CommentBean commentArticle) {
		
		boolean insertCount = false;
		
		try {
			con = DBConnection.getConnection();
			
			con.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into boardComment (commentID, boardID, memberID, date, parent, content) values(?, ?, ?, now(), ?, ?)");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, commentArticle.getCommentID());
			pstmt.setInt(2, commentArticle.getBoardID());
			pstmt.setString(3, commentArticle.getMemberID());
			pstmt.setInt(4, commentArticle.getParentID());
			pstmt.setString(5, commentArticle.getContent());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				insertCount = true;
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
		return insertCount;
	}
	
	//댓글 목록 보기
	public ArrayList<CommentBean> selectCommentList(int boardID){
        
		ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
        
        try {
            con = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select level, commentID, boardID, memberID, date, parentID, content");
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, boardID);
            
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
                commentList.add(comment);
            }
                
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return commentList;
    }
	
	// 댓글 1개의 정보를 가져온다.
    public CommentBean getComment(int commentID)
    {
        CommentBean comment = null;
        
        try {
            con = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select * from boardComment where commentID = ?");
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, commentID);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            	comment = new CommentBean();
            	comment.setCommentID(rs.getInt("commentID"));
            	comment.setBoardID(rs.getInt("boardID"));
            	comment.setMemberID(rs.getString("memberID"));
            	comment.setDate(rs.getDate("date"));
            	comment.setParentID(rs.getInt("parentID"));
            	comment.setContent(rs.getString("content"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return comment; 
    } // end getGuestbook
    
    // 댓글 삭제
    public boolean deleteComment(int commentID) 
    {
        boolean deleteCount = false;
 
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
 
            StringBuffer sql = new StringBuffer();
            sql.append("delete from boardComment where commentID in (select commentID from boardComment start with commentID = ? connect by prior commentID = parentID");
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, commentID);
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
            	deleteCount = true;
                con.commit(); // 완료시 커밋
            }    
            
        } catch (Exception e) {
            try {
                con.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
 
        close();
        return deleteCount;
    } // end deleteComment    
    
    
    // 댓글 수정
    public boolean updateComment(CommentBean comment) 
    {
        boolean updateCount = false;
        
        try{
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            StringBuffer sql = new StringBuffer();
            sql.append("update boardComment set content = ? where commentID = ?");
            
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getCommentID());
 
            int flag = pstmt.executeUpdate();
            if(flag > 0){
            	updateCount = true;
                con.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                con.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    
        close();
        return updateCount;
    } // end updateComment    
       
    
    // DB 자원해제
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
