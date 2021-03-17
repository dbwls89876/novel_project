package vo;

public class FundingGoods {
	
	public FundingGoods(int fundingID, int goodsID, String name, int cost, int count, int maxNumber) {
		super();
		this.fundingID = fundingID;
		this.goodsID = goodsID;
		this.name = name;
		this.cost = cost;
		this.count = count;
		this.maxNumber = maxNumber;
	}
	int fundingID;
	int goodsID;
	String name;
	int cost;
	int count;
	int maxNumber;
	
	public FundingGoods() {}
	
	public int getFundingID() {
		return fundingID;
	}
	public void setFundingID(int fundingID) {
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
