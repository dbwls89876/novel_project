package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("*.comm")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/commentWriteForm.comm")) {
			forward = new ActionForward();
			forward.setPath("board/boardVeiw.jsp");
		}else if(command.equals("/commentWritePro.comm")) {
			action = new CommentWriteProAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/commentList.comm")) {
			action = new CommentListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/commentModifyForm.comm")) {
			action = new CommentModifyFormAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/commentModifyPro.comm")) {
			action = new CommentModifyProAction();
			try {
				forward = action.execute(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/commentDeleteForm.comm")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int boardID = Integer.parseInt(request.getParameter("boardID"));
			request.setAttribute("boardID", boardID);
			forward = new ActionForward();
			forward.setPath("/board/boardDelete.jsp");
		}
		else if (command.contentEquals("/boardDeletePro.bo")) {	
			action = new BoardDeleteProAction();
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
