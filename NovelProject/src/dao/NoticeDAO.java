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
	
	private NoticeDAO() {}
	
	public static NoticeDAO getInstance() {
		if(noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//글 등록
	public int insertArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount=0;
		
		try {
			pstmt = con.prepareStatement("select max(noticeID) from notice");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;
			else
				num=1;
			
			sql="insert into notice (noticeID, id, title,";
			sql+="content, ref, "+"lev, seq, readCount," + "date) values(?,?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getId());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("noticeInsert : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//글의 개수 구하기
	public int selectListCount() {
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			pstmt = con.prepareStatement("select count(*) from notice");
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
	
	//글 삭제
	public int deleteArticle(int noticeID) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from notice where noticeID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeID);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	//글 목록 보기
	public ArrayList<BoardBean> selectArticleList(int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String noticeList_sql = "select * from notice order by ref desc, seq asc limit ?, ?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean notice = null;;
		int startrow = (page-1)*limit;
		
		try {
			pstmt = con.prepareStatement(noticeList_sql);
			pstmt.setInt(1,  startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				notice = new BoardBean();
				notice.setNoticeID(rs.getInt("noticeID"));
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRef(rs.getInt("ref"));
				notice.setLev(rs.getInt("lev"));
				notice.setSeq(rs.getInt("seq"));
				notice.setReadCount(rs.getInt("readCount"));
				notice.setDate(rs.getDate("date"));
				articleList.add(notice);
			}
		}catch(Exception e){
			System.out.println("getNoticeList 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	//글 내용 보기
	public BoardBean selectArticle(int noticeID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		
		try {
			pstmt = con.prepareStatement("select * from notice where noticeID = ?");
			pstmt.setInt(1, noticeID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoardID(rs.getInt("noticeID"));
				boardBean.setId(rs.getInt("id"));
				boardBean.setTitle(rs.getString("title"));
				boardBean.setContent(rs.getString("content"));
				boardBean.setRef(rs.getInt("ref"));
				boardBean.setLev(rs.getInt("lev"));
				boardBean.setSeq(rs.getInt("seq"));
				boardBean.setReadCount(rs.getInt("readCount"));
				boardBean.setDate(rs.getDate("date"));
			}
		}catch(Exception e) {
			System.out.println("getDetail 오류 : "  + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardBean;
	}
	
	//글 답변
	public int insertReplyArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String noticeMax_sql = "select max(noticeID) from notice";
		String sql="";
		int num = 0;
		int insertCount = 0;
		int ref = article.getRef();
		int lev = article.getLev();
		int seq = article.getSeq();
		
		try {
			pstmt = con.prepareStatement(noticeMax_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num = rs.getInt(1) + 1;
			else num=1;
			
			sql = "update notice set seq=eq+1 where ref=?";
			sql+="and seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount>0) {
				commit(con);
			}
			
			seq = seq + 1;
			lev = lev + 1;
			sql = "insert into notice (noticeID, id, title,";
			sql += "content, ref, lev, seq, ";
			sql += "readCount, date) values(?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, article.getId());
			pstmt.setString(3, article.getTitle());
			pstmt.setString(4, article.getContent());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, lev);
			pstmt.setInt(7, seq);
			pstmt.setInt(8, 0);
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("noticeReply 오류 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	//조회수 기능
	public int updateReadCount(int noticeID) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update notice set readCount = " +
		"readCount+1 where noticeID = " + noticeID;
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
	
	//글쓴이인지 확인
	public boolean isArticleBoardWriter(int noticeID, int id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String notice_sql = "select * from notice where noticeID=?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(notice_sql);
			pstmt.setInt(1, noticeID);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(id==rs.getInt("id")) {
				isWriter = true;
			}
		}catch(SQLException e) {
			System.out.println("isNoticeWriter 오류 : " + e);
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