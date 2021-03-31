package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("*.comm")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String,Action> commandMap;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {	
        loadProperties("jsp/board/comment/properties/CommentCommand");
    }

	/**
	 * ������Ƽ ���Ͽ��� Ű���� Ŭ���� ������ �����Ͽ� �װ��� Map�� �����Ѵ�.
	 * @param filePath ������Ƽ ������ ���
	 */
	private void loadProperties(String filePath) 
	{
		commandMap = new HashMap<String, Action>();
		
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Enumeration<String> actionEnum = rb.getKeys(); // Ű���� �����´�.
		 
		while (actionEnum.hasMoreElements()) 
		{
			String command = actionEnum.nextElement(); 
			String className = rb.getString(command); 
			
			try {
				 Class actionClass = Class.forName(className); // Ŭ���� ����
				 Action actionInstance = (Action)actionClass.newInstance(); // Ŭ������ ��ü�� ����
				 
				 commandMap.put(command, actionInstance);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		
		String requestURI = request.getRequestURI();
        int cmdIdx = requestURI.lastIndexOf("/") + 1;
        String command = requestURI.substring(cmdIdx);
 
        ActionForward forward = null;
        Action action = null;
        
        try {
            action = commandMap.get(command);
            
            if (action == null) {
                System.out.println("명령어 : "+command+"는 잘못된 명령입니다.");
                return;
            }
 
            forward = action.execute(request, response);
            
            
            if(forward != null){
                if (forward.isRedirect()) {
                    response.sendRedirect(forward.getPath());
                } else {
                    RequestDispatcher dispatcher = request
                            .getRequestDispatcher(forward.getPath());
                    dispatcher.forward(request, response);
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
