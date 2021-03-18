package vo;

import java.sql.Date;

public class Customer {
	int id;
	int supportID;
	int goodsID;
	Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSupportID() {
		return supportID;
	}
	public void setSupportID(int supportID) {
		this.supportID = supportID;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
