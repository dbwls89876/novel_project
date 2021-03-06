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

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 글의 개수 구하기
	public int selectListCount() {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	// 글 목록 보기
	public ArrayList<BoardBean> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String boardList_sql = "select * from board order by replyID desc, replyStep asc limit ?, 10";

		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page - 1) * 10;

		try {
			pstmt = con.prepareStatement(boardList_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new BoardBean();
				board.setBoardID(rs.getInt("boardID"));
				board.setMemberID(rs.getString("memberID"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReplyID(rs.getInt("replyID"));
				board.setReplyLV(rs.getInt("replyLV"));
				board.setReplyStep(rs.getInt("replyStep"));
				board.setReadCount(rs.getInt("readCount"));
				board.setDate(rs.getDate("date"));
				articleList.add(board);
			}

		} catch (Exception e) {
			System.out.println("getBoardList 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	// 글 내용 보기
	public BoardBean selectArticle(int boardID) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;

		try {
			pstmt = con.prepareStatement("select * from board where boardID = ?");
			pstmt.setInt(1, boardID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoardID(rs.getInt("boardID"));
				boardBean.setMemberID(rs.getString("memberID"));
				boardBean.setTitle(rs.getString("title"));
				boardBean.setContent(rs.getString("content"));
				boardBean.setReplyID(rs.getInt("replyID"));
				boardBean.setReplyLV(rs.getInt("replyLV"));
				boardBean.setReplyStep(rs.getInt("replyStep"));
				boardBean.setReadCount(rs.getInt("readCount"));
				boardBean.setDate(rs.getDate("date"));
			}
		} catch (Exception e) {
			System.out.println("getDetail 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return boardBean;

	}

	// 글 등록
	public int insertArticle(BoardBean article) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;

		try {
			pstmt = con.prepareStatement("select max(boardID) from board");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into board (boardID, memberID, title, content, replyID, replyLV, replyStep, readCount, date) values(?,?,?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMemberID());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("boardInsert 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return insertCount;
	}
	
	// 글 답변
	public int insertReplyArticle(BoardBean article) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String boardReply_sql = "select max(boardID) from board";
		String sql = "";
		int num = 0;
		int insertCount = 0;
		int d_replyID = article.getReplyID();
		int d_replyLV = article.getReplyLV();
		int d_replyStep = article.getReplyStep();
		
		try {
			pstmt = con.prepareStatement(boardReply_sql);
			rs = pstmt.executeQuery();
			if(rs.next()) num = rs.getInt(1)+1;
			else num = 1;
			sql = "update board set replyStep = replyStep+1 where replyID=? and replyStep>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, d_replyID);
			pstmt.setInt(2, d_replyStep);
			int updateCount = pstmt.executeUpdate();
			System.out.println("action: " + article.getMemberID());
			
			if(updateCount > 0) {
				commit(con);
			}
			
			d_replyStep = d_replyStep + 1;
			d_replyLV = d_replyLV + 1;
			sql = "insert into board (boardID, memberID, title, content, replyID, replyLV, replyStep, readCount, date) values (?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMemberID());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, d_replyID);
			pstmt.setInt(6, d_replyLV);
			pstmt.setInt(7, d_replyStep);
			pstmt.setInt(8, 0);
			insertCount = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Reply 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	// 글 수정
	public int updateArticle(BoardBean article) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set title = ?, content = ? where boardID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getBoardID());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardModify 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글 삭제
	public int deleteArticle(int boardID) {

		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String boardDelete_sql = "delete from board where boardID = ?";

		try {
			pstmt = con.prepareStatement(boardDelete_sql);
			pstmt.setInt(1, boardID);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardDelete 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// 조회수 기능
	public int updateReadCount(int boardID) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update board set readCount = readCount+1 where boardID = " + boardID;

		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("setReadCountUpdate 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return updateCount;

	}

	// 글쓴이인지 확인
	public boolean isArticleBoardWriter(int boardID, String memberID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql = "select * from board where boardID = ?";
		boolean isWriter = false;

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, boardID);
			rs = pstmt.executeQuery();
			rs.next();

			if (memberID.equals(rs.getString("memberID"))) {
				isWriter = true;
			}
		} catch (SQLException e) {
			System.out.println("isBoardWriter 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return isWriter;
	}


}