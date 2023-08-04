<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 전송 데이터 수신
	String uid     = request.getParameter("uid");
	String name    = request.getParameter("name");
	String birth   = request.getParameter("birth");
	String gender  = request.getParameter("gender");
	String age     = request.getParameter("age");
	String address = request.getParameter("address");
	String hp      = request.getParameter("hp");
	
	// 데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user5` VALUES (?, ?, ?, ?, ?, ?, ?)");
		psmt.setString(1, uid);
		psmt.setString(2, name);
		psmt.setString(3, birth);
		psmt.setString(4, gender);
		psmt.setString(5, age);
		psmt.setString(6, address);
		psmt.setString(7, hp);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	response.sendRedirect("/Ch06/user5/list.jsp");
%>