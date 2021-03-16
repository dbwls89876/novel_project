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
		String[] fundingIDList = request.getParameterValues("fundingIDList");
		FundingPermissionUpdateService fundingPermissionUpdateService = new FundingPermissionUpdateService();
		fundingPermissionUpdateService.PermissionUpdate(fundingIDList);
		forward = new ActionForward("/funding/fundingPermission.jsp", true);
		return forward;
	}

}
