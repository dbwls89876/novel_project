package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import funding.action.*;
import vo.ActionForward;

/**
 * Servlet implementation class FundingFrontController
 */
@WebServlet("*.fun")
public class FundingFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward=null;
		Action action=null;
		System.out.println(command);
		
		if(command.equals("/weekFunding.fun")) {
			action = new WeekFundingAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/totalFunding.fun")) {
			action = new TotalFundingAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/moveRegister.fun")){
			action = new MoveRegisterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/fundingRegister.fun")) {
			action = new FundingRegisterAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/fundingPermission.fun")) {
			action = new FundingPermissionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/fundingPermissionUpdate.fun")) {
			action = new FundingPermissionUpdateAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/fundingContent.fun")) {
			try {
				action = new FundingContentAction();
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/fundingPayment.fun")) {
			action = new FundingPaymentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}

}
