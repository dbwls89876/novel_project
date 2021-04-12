package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BoardBean;

public class NoticeDAO {
	DataSource ds;
	Connection con;
	private static NoticeDAO noticeDAO;

	private NoticeDAO() {

	}

	public static NoticeDAO getInstance() {
		if (noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
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
			pstmt = con.prepareStatement("select count(*) from notice");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getListCount : " + e);
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
		
		String noticeList_sql = "select noticeID, title, memberID, date, readCount from notice order by noticeID desc limit ?, ?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean notice = null;
		int startrow = (page - 1) * limit;

		try {
			pstmt = con.prepareStatement(noticeList_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				notice = new BoardBean();
				notice.setNoticeID(rs.getInt("noticeID"));
				notice.setTitle(rs.getString("title"));
				notice.setMemberID(rs.getString("memberID"));
				notice.setDate(rs.getDate("date"));
				notice.setReadCount(rs.getInt("readCount"));
				articleList.add(notice);
			}
		} catch (Exception e) {
			System.out.println("getNoticeList 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	// 글 내용 보기
	public BoardBean selectArticle(int noticeID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;

		try {
			pstmt = con.prepareStatement("select * from notice where noticeID = ?");
			pstmt.setInt(1, noticeID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setNoticeID(rs.getInt("noticeID"));
				boardBean.setMemberID(rs.getString("memberID"));
				boardBean.setTitle(rs.getString("title"));
				boardBean.setContent(rs.getString("content"));
				boardBean.setFile(rs.getString("file"));
				boardBean.setReadCount(rs.getInt("readCount"));
				boardBean.setDate(rs.getDate("date"));
			}
		} catch (Exception e) {
			System.out.println("getDetail 에러 : " + e);
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
			pstmt = con.prepareStatement("select max(noticeID) from notice");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "insert into notice (noticeID, memberID, title, content, file, readCount, date) values(?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMemberID());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setString(5, article.getFile());
			pstmt.setInt(6, num);

			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("noticeInsert 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//글 수정
	public int updateArticle(BoardBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update notice set title = ?, content = ?, file=? where noticeID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getFile());
			pstmt.setInt(4, article.getNoticeID());
			updateCount = pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("noticeModify 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글 삭제
	public int deleteArticle(int noticeID) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String noticeDelete_sql = "delete from notice where noticeID = ?";
		try {
			pstmt = con.prepareStatement(noticeDelete_sql);
			pstmt.setInt(1, noticeID);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("noticeDelete 에러 : " + e);
		} finally {
			close(pstmt);
		}
		return deleteCount;
	}

	// 조회수 기능
	public int updateReadCount(int noticeID) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update notice set readCount = " + "readCount+1 where noticeID = " + noticeID;
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("setReadCountUpdate 오류 : " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	// 글쓴이인지 확인
	public boolean isArticleNoticeWriter(int noticeID, String memberID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String notice_sql = "select * from notice where noticeID=?";
		boolean isWriter = false;

		try {
			pstmt = con.prepareStatement(notice_sql);
			pstmt.setInt(1, noticeID);
			rs = pstmt.executeQuery();
			rs.next();

			if (memberID.equals(rs.getString("memberID"))) {
				isWriter = true;
			}
		} catch (SQLException e) {
			System.out.println("isNoticeWriter 에러  : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return isWriter;
	}

	

}
