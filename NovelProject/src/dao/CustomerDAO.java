package dao;

import java.sql.Connection;

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
}
