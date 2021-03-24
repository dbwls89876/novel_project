package funding.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingRegisterService;
import vo.ActionForward;
import vo.Funding;
import vo.FundingGoods;

public class FundingRegisterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=null;
		Funding funding = new Funding();
		ArrayList<FundingGoods> fundingGoodsList = new ArrayList<FundingGoods>();
		FundingGoods fundingGoods = new FundingGoods();
		Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));
		Date deliveryDate = java.sql.Date.valueOf(request.getParameter("deliveryDate"));
		String[] nameList = request.getParameterValues("name");
		String[] costList = request.getParameterValues("cost");
		String[] maxNumberList = request.getParameterValues("maxNumber");
		int literaryID = Integer.parseInt(request.getParameter("selectLiterary"));
		funding.setLiteraryID(literaryID);
		funding.setTitle(request.getParameter("title"));
		funding.setContent(request.getParameter("content"));
		funding.setImage(request.getParameter("image"));
		funding.setTargetCost(Integer.parseInt(request.getParameter("targetCost")));
		funding.setEndDate(endDate);
		funding.setDeliveryDate(deliveryDate);
		
		//받아온 굿즈 정보만큼 fundingGoodsList에 add
		for(int i = 0; i<nameList.length; i++) {
			fundingGoodsList.get(i).setName(nameList[i]);
			fundingGoodsList.get(i).setCost(Integer.parseInt(costList[i]));
			fundingGoodsList.get(i).setMaxNumber(Integer.parseInt(maxNumberList[i]));
			
			System.out.println("get0 계속 확인 : " + fundingGoodsList.get(0).getName());
			System.out.println("namelist  : " + nameList[i]);
			//System.out.println("action, fundinggoods : " + fundingGoods.getName());
			System.out.println("list : " + fundingGoodsList.get(i).getName());
		}
		
		System.out.println("acget0 : " + fundingGoodsList.get(0).getName());
		System.out.println("acget1 : " + fundingGoodsList.get(1).getName());
		FundingRegisterService fundingRegisterService = new FundingRegisterService();
		fundingRegisterService.registerFunding(funding, fundingGoodsList);
		forward = new ActionForward("/totalFunding.fun", true);
		return forward;
	}

}
