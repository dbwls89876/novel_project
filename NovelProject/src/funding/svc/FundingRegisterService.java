package funding.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FundingDAO;
import dao.FundingGoodsDAO;
import vo.Funding;
import vo.FundingGoods;

import static db.JdbcUtil.*;

public class FundingRegisterService {
	public void registerFunding(Funding funding, ArrayList<FundingGoods> fundingGoodsList) {
		boolean isRegisterSuccess1 = false;
		boolean isRegisterSuccess2 = false;
		Connection con = null;
		try {
			con = getConnection();
			//fundingDAO에 funding정보 insert
			FundingDAO fundingDAO = FundingDAO.getInstance();
			fundingDAO.setConnection(con);
			isRegisterSuccess1 = fundingDAO.insertFunding(funding);
			//fundingGoods 추가를 위해 fundingID get
			int fundingID = fundingDAO.selectFundingID(funding);
			//System.out.println("serget0 : " + fundingGoodsList.get(0).getName());
			//System.out.println("serget1 : " + fundingGoodsList.get(1).getName());
			FundingGoodsDAO fundingGoodsDAO = FundingGoodsDAO.getInstance();
			fundingGoodsDAO.setConnection(con);
			for(int i = 0; i<fundingGoodsList.size(); i++) {
				FundingGoods fundingGoods = fundingGoodsList.get(i);
				//System.out.println("서비스 fundinggods : " + fundingGoods.getName());
				isRegisterSuccess2 = fundingGoodsDAO.insertFundingGoods(fundingID, fundingGoods);
				//false 발생 시 for문 탈출, 커밋 실패
				if(isRegisterSuccess2 == false) {
					break;
				}
			}
			
			if(isRegisterSuccess1 && isRegisterSuccess2) {
				commit(con);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rollback(con);
		}finally {
			close(con);
		}
		
	}
}
