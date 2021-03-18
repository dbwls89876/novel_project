package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class CustomerDAO {
	DataSource ds;
	Connection con;
	private static CustomerDAO customerDAO;
	private CustomerDAO() {}
	
	public static CustomerDAO getInstance() {
		if(customerDAO == null) {
			customerDAO = new CustomerDAO();
		}
		return customerDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public boolean insertCustomer(int id, int goodsID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isInsertSucess = false;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(date);
		String sql = "insert into customer (id, goodsID, date) values (?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, goodsID);
			pstmt.setString(3, today);
			pstmt.execute();
			isInsertSucess = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return isInsertSucess;
	}
}
