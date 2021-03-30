<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	request.setCharacterEncoding("UTF-8");	
	String chk_id = request.getParameter("memberID");
		
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null; //select문 사용시 넣으면 됨 아니면 빼기
	
	String sql="select * from member where memberID=?";
	try{
		DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mySQLDB");
		conn=ds.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, chk_id);
		rs=pstmt.executeQuery();
		
		if(rs.next()){
			response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=no");//띄어쓰기하면 안됨~~
		}else{
			response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=yes");
		}
	
	}catch (Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
		if(conn != null)
			conn.close();

		}	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>