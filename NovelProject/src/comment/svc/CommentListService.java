package comment.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.CommentDAO;
import vo.CommentBean;

public class CommentListService {

	public int getListCount() throws Exception {

		int listCount = 0;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		listCount = commentDAO.selectListCount();
		close(con);
		return listCount;
	}

	public ArrayList<CommentBean> getArticleList(int page, int limit) throws Exception{

		ArrayList<CommentBean> articleList = null;
		Connection con = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(con);
		articleList = commentDAO.selectArticleList(page, limit);
		close(con);
		return articleList;
	}

}
