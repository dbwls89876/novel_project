<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	request.setCharacterEncoding("UTF-8");	
	String chk_nickname = request.getParameter("nickname");
	System.out.println(chk_nickname);	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null; //select문 사용시 넣으면 됨 아니면 빼기
	
	String sql="select * from member where nickname=?";

		DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mySQLDB");
		conn=ds.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, chk_nickname);
		rs=pstmt.executeQuery();
		
		if(rs.next()){
			%>
		<script>
			location.href="nicknameCheck.jsp?chk_nickname="+encodeURIComponent('<%=chk_nickname%>')+"&useble=no";
		</script>	
			<%
		}else{
			%>
		<script>
			location.href="nicknameCheck.jsp?chk_nickname="+encodeURIComponent('<%=chk_nickname%>')+"&useble=yes";
		</script>	
			<%
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