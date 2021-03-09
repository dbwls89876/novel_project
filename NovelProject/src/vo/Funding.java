package vo;

import java.sql.Date;

/* 펀딩 리스트를 불러오는 기능
 * 
 * 
 */
public class Funding {
	int literaryID;
	int fundingID;
	String title;
	String content;
	String image;
	int targetCost;
	int nowCost;
	Date startDate;
	Date endDate;
	
	public int getLiteraryID() {
		return literaryID;
	}
	public void setLiteraryID(int literaryID) {
		this.literaryID = literaryID;
	}
	public int getFundingID() {
		return fundingID;
	}
	public void setFundingID(int fundingID) {
		this.fundingID = fundingID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTargetCost() {
		return targetCost;
	}
	public void setTargetCost(int targetCost) {
		this.targetCost = targetCost;
	}
	public int getNowCost() {
		return nowCost;
	}
	public void setNowCost(int nowCost) {
		this.nowCost = nowCost;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
