package funding.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingPermissionService;
import vo.ActionForward;
import vo.Funding;

public class FundingPermissionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Funding> permissionWaitingList = new ArrayList<Funding>();
		ArrayList<Funding> fundingList = null;
		FundingPermissionService fundingPermissionService = new FundingPermissionService();
		
		fundingList = fundingPermissionService.permissionWaitingList();
		
		for(int i = 0; i<fundingList.size(); i++) {
			if(fundingList.get(i).getPermission() == 0) {
				permissionWaitingList.add(fundingList.get(i));
			}
		}
		request.setAttribute("permissionWaitingList", permissionWaitingList);
		forward = new ActionForward("/admin/fundingPermission.jsp", true);
		return forward;
	}

}
