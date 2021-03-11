package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BoardBean;

public class BoardDAO {
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
//	public boolean registArticle(BoardBean boardBean) {
//		boolean isWriteSuccess = false;
//		Connection con = null;
//		
//		try {
//			con = getConnection();
//			BoardDAO boardDAO = 
//		}
//	}
	
	public int insertArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount=0;
		
		try {
			pstmt = con.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			
			sql="insert into board (board_num, board_name, board_pass, board_subject,";
			sql+="board_content, board_file, board_re_ref, "+"board_re_lev, board_re_seq, board_readcount," + "board_date) values(?,?,?,?,?,?,?,0,0,0,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, num);
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardInsert : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	//湲� 紐⑸줉 蹂닿린
	
	public int deleteArticle(int board_num) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from board where board_num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	public ArrayList<BoardBean> selectArticleList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql = "select * from board order by board_re_ref desc, board_re_seq asc limit ?, ?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;;
		int startrow = (page-1)*limit;
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
				articleList.add(board);
			}
		}catch(Exception e){
			System.out.println("getBoardList 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	//湲��궡�슜蹂닿린
	public BoardBean selectArticle(int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from board where board_num = ?");
			pstmt.setInt(1,  board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoard_num(rs.getInt("board_num"));
				boardBean.setBoard_name(rs.getString("board_name"));
				boardBean.setBoard_subject(rs.getString("board_subject"));
				boardBean.setBoard_content(rs.getString("board_content"));
				boardBean.setBoard_file(rs.getString("board_file"));
				boardBean.setBoard_re_ref(rs.getInt("board_re_ref"));
				boardBean.setBoard_re_lev(rs.getInt("board_re_lev"));
				boardBean.setBoard_re_seq(rs.getInt("board_re_seq"));
				boardBean.setBoard_readcount(rs.getInt("board_readcount"));
				boardBean.setBoard_date(rs.getDate("board_date"));
			}
		}catch(Exception e) {
			System.out.println("getDetail 오류 : "  + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardBean;
	}
	//湲��떟蹂�
	public int insertReplyArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql = "select max(board_num) from board";
		String sql="";
		int num = 0;
		int insertCount = 0;
		int re_ref = article.getBoard_re_ref();
		int re_lev = article.getBoard_re_lev();
		int re_seq = article.getBoard_re_seq();
		
		try {
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num = rs.getInt(1) + 1;
			else num=1;
			
			sql = "update board set board_re_seq=board_re_seq+1 where board_re_ref=?";
			sql+="and board_re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(con);
			}
			
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			sql = "insert into board (board_num, board_name, board_pass, board_subject,";
			sql += "board_content, board_file, board_re_ref, board_re_lev, board_re_seq, ";
			sql += "board_readcount, board_date_ values(?,?,?,?,?,?,?,?,?,0,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, ""); // �떟�옣 �뙆�씪 �뾽濡쒕뱶 x
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("boardReply 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//조회수 기능
	public int updateReadCount(int board_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update board set board_readcount = " +
		"board_readcount+1 where board_num = " + board_num;
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("setReadCountUpdate 오류 : " + e);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//글쓰기 기능
	public boolean isArticleBoardWriter(int boardID, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql = "select * from board where board_num=?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, boardID);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(id.equals(rs.getString("id"))) {
				isWriter = true;
			}
		}catch(SQLException e) {
			System.out.println("isBoardWriter 오류 : " + e);
		}finally {
			close(pstmt);
		}
		
		return isWriter;
	}

	public int deleteArticle(BoardBean article) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
