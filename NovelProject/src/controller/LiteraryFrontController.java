package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import literary.action.NewLiteraryListAction;
import literary.action.LiteraryRegistAction;
import literary.action.TotalLiteraryListAction;
import literary.action.MyLiteraryListAction;
import vo.ActionForward;
import literary.action.LiteraryRegistFormAction;

/**
 * Servlet implementation class LiteraryFrontController
 */
@WebServlet("*.lit")
public class LiteraryFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiteraryFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		System.out.println(command);
		ActionForward forward = null;
		Action action=null;
		System.out.println(command);
		
		if(command.equals("/newLiteraryList.lit")) {
			action = new NewLiteraryListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
					
		}
		else if(command.equals("/totalLiteraryList.lit")) {
			action = new TotalLiteraryListAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/literaryRegistForm.lit")) { 
			action = new LiteraryRegistFormAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/literaryRegist.lit")) { //새로운 작품 등록
			action = new LiteraryRegistAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/myLiteraryList.lit")) { 
			action = new MyLiteraryListAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
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
