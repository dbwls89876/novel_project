package vo;

public class FundingGoods {
	String fundingID;
	int goodsID;
	String name;
	int cost;
	int count;
	int maxNumber;
	public String getFundingID() {
		return fundingID;
	}
	public void setFundingID(String fundingID) {
		this.fundingID = fundingID;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
}
