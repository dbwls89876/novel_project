package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.CommentBean;

public class CommentDAO {

	DataSource ds;
	Connection con;
	private static CommentDAO commentDAO;
	
	private CommentDAO() {
		
	}
	
	public static CommentDAO getInstance() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO();
		}
		return commentDAO;
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
			pstmt = con.prepareStatement("select count(*) from boardComment");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("getListCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		} return listCount;
	}
	
	// 글 목록 보기
		public ArrayList<CommentBean> selectArticleList(int page, int limit) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String boardList_sql = "select memberID, date, content from boardComment order by commentID desc limit ?, ?";

			ArrayList<CommentBean> articleList = new ArrayList<CommentBean>();
			CommentBean boardComment = null;
			int startrow = (page - 1) * limit;

			try {
				pstmt = con.prepareStatement(boardList_sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					boardComment = new CommentBean();
					boardComment.setMemberID(rs.getString("memberID"));
					boardComment.setDate(rs.getDate("date"));
					boardComment.setContent(rs.getString("content"));
					articleList.add(boardComment);
				}

			} catch (Exception e) {
				System.out.println("getBoardCommentList 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			return articleList;

		}

		// 글 내용 보기
		public CommentBean selectArticle(int commentID) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CommentBean boardComment = null;

			try {
				pstmt = con.prepareStatement("select memberID, date, content from boardComment where commentID = ?");
				pstmt.setInt(1, commentID);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					boardComment = new CommentBean();
					boardComment.setMemberID(rs.getString("memberID"));
					boardComment.setContent(rs.getString("content"));
					boardComment.setDate(rs.getDate("date"));
				}
			} catch (Exception e) {
				System.out.println("getDetail 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			return boardComment;

		}

		// 글 등록
		public int insertArticle(CommentBean article) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num = 0;
			String sql = "";
			int insertCount = 0;

			try {
				pstmt = con.prepareStatement("select max(commentID) from boardComment");
				rs = pstmt.executeQuery();

				if (rs.next())
					num = rs.getInt(1) + 1;
				else
					num = 1;

				sql = "insert into boardComment (commentID, boardID, memberID, content, date) values(?,?,?,?,?,now())";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, article.getBoardID());
				pstmt.setString(3, article.getMemberID());
				pstmt.setString(4, article.getContent());

				insertCount = pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("boardCommentInsert 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}

			return insertCount;
		}

		// 글 수정
		public int updateArticle(CommentBean article) {

			int updateCount = 0;
			PreparedStatement pstmt = null;
			String sql = "update board set content = ? where commentID = ?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getContent());
				pstmt.setInt(2, article.getCommentID());
				updateCount = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("boardCommentModify 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return updateCount;
		}

		// 글 삭제
		public int deleteArticle(int commentID) {

			int deleteCount = 0;
			PreparedStatement pstmt = null;
			String boardDelete_sql = "delete from boardComment where commentID = ?";

			try {
				pstmt = con.prepareStatement(boardDelete_sql);
				pstmt.setInt(1, commentID);
				deleteCount = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("boardCommentDelete 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return deleteCount;
		}

		// 글쓴이인지 확인
		public boolean isArticleBoardWriter(int commentID, String memberID) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String boardComment_sql = "select * from boardComment where commentID = ?";
			boolean isWriter = false;

			try {
				pstmt = con.prepareStatement(boardComment_sql);
				pstmt.setInt(1, commentID);
				rs = pstmt.executeQuery();
				rs.next();

				if (memberID.equals(rs.getString("memberID"))) {
					isWriter = true;
				}
			} catch (SQLException e) {
				System.out.println("isBoardCommentWriter 에러 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
			}

			return isWriter;
		}

	
	
	
	
}
