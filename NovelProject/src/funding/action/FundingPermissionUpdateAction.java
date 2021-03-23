package funding.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.svc.FundingPermissionUpdateService;
import vo.ActionForward;

public class FundingPermissionUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String[] fundingList = request.getParameterValues("fundingList");
		FundingPermissionUpdateService fundingPermissionUpdateService = new FundingPermissionUpdateService();
		fundingPermissionUpdateService.PermissionUpdate(fundingList);
		forward = new ActionForward("/fundingPermission.fun", true);
		return forward;
	}

}
