package funding.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.sql.Date;

import action.Action;
import funding.svc.FundingRegisterService;
import vo.ActionForward;
import vo.Funding;
import vo.FundingGoods;

public class FundingRegisterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//파일 업로드
		String realPath = "";
		String uploadPath = "/fundingImages";
		int size = 10*1024*1024;
		ServletContext context = request.getServletContext();
		realPath = context.getRealPath(uploadPath);
		System.out.println(realPath);
		
		MultipartRequest multi = new MultipartRequest(request, realPath, size, "UTF-8", new DefaultFileRenamePolicy());
		String fileName = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		System.out.println(fileName);
		
		ActionForward forward=null;
		Funding funding = new Funding();
		ArrayList<FundingGoods> fundingGoodsList = new ArrayList<FundingGoods>();
		FundingGoods fundingGoods = null;
		Date endDate = java.sql.Date.valueOf(multi.getParameter("endDate"));
		Date deliveryDate = java.sql.Date.valueOf(multi.getParameter("deliveryDate"));
		String[] nameList = multi.getParameterValues("name");
		String[] costList = multi.getParameterValues("cost");
		String[] maxNumberList = multi.getParameterValues("maxNumber");
		int literaryID = Integer.parseInt(multi.getParameter("selectLiterary"));
		
		
		funding.setLiteraryID(literaryID);
		funding.setTitle(multi.getParameter("title"));
		funding.setContent(multi.getParameter("content"));
		funding.setImage(multi.getParameter("image"));
		funding.setTargetCost(Integer.parseInt(multi.getParameter("targetCost")));
		funding.setEndDate(endDate);
		funding.setDeliveryDate(deliveryDate);
		
		//받아온 굿즈 정보만큼 fundingGoodsList에 add
		for(int i = 0; i<nameList.length; i++) {
			fundingGoods = new FundingGoods();
			fundingGoods.setName(nameList[i]);
			fundingGoods.setCost(Integer.parseInt(costList[i]));
			fundingGoods.setMaxNumber(Integer.parseInt(maxNumberList[i]));
			fundingGoodsList.add(fundingGoods);
		}

		FundingRegisterService fundingRegisterService = new FundingRegisterService();
		fundingRegisterService.registerFunding(funding, fundingGoodsList);
		forward = new ActionForward("/totalFunding.fun", true);
		return forward;
	}

}
