package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;
import member.action.*;
/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
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
		System.out.println(command);
		ActionForward forward=null;
		Action action=null;
		System.out.println(command);
		
		if(command.equals("/memberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/member/loginForm.jsp");
		}else if(command.equals("/memberJoin.me")) { 
			action = new MemberLoginProAction(); 
			try { 
				forward = action.execute(request, response);
			}catch(Exception e) { 
				e.printStackTrace(); 
			} 
		}else if(command.equals("/logout.log")) {
			HttpSession session = request.getSession();
			session.invalidate();
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.jsp");
		}else if(command.equals("/joinForm.mem")){
			forward = new ActionForward();
			forward.setPath("/member/joinForm.jsp");
		}else if(command.equals("/joinProcess.mem")) {
			action = new JoinProAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberList.mem")) { 
			action = new MemberListAction(); 
			try {
				forward = action.execute(request, response);
			}catch(Exception e) { 
				e.printStackTrace(); 
			}
		}else if(command.equals("/memberInfo.mem")) {
			action = new MemberInfoAction(); 
			try {
				forward = action.execute(request, response);
			}catch(Exception e) { 
				e.printStackTrace(); 
			}
		}else if(command.equals("/memberModForm.mem")) {
			action = new MemberModFormAction();
			try {
			forward = action.execute(request, response);
			}catch(Exception e) { 
				e.printStackTrace(); 
			}
		}else if(command.equals("/memberModePro.mem")) {
			action = new MemberModProAction(); 
			try {
				forward = action.execute(request, response);
			}catch(Exception e) { 
				e.printStackTrace(); 
			}
		}else if(command.equals("/memberDel.mem")) {
			action = new MemberDeleteAction(); 
			try {
				forward = action.execute(request, response);
			}catch(Exception e) { 
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
