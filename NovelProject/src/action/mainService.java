package action;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;
import dao.LiteraryDAO;

import static db.JdbcUtil.*;
import vo.Funding;
import vo.Literary;

public class mainService {
	public ArrayList<Funding> getRecentFunding() {
		Connection con = null;
		ArrayList<Funding> fundingList = null;
		
		try {
			FundingDAO fundingDAO = FundingDAO.getInstance();
			con = getConnection();
			fundingDAO.setConnection(con);
			fundingList = fundingDAO.selectRecentFunding();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return fundingList;
	}
	
	public ArrayList<Literary> getRecentLiterary(){
		Connection con = null;
		ArrayList<Literary> literaryList = null;
		
		try {
			LiteraryDAO literaryDAO = LiteraryDAO.getInstance();
			con = getConnection();
			literaryDAO.setConnection(con);
			literaryList = literaryDAO.selectRecentArtistLiteraryList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(con);
		}
		
		return literaryList;
	}
}
