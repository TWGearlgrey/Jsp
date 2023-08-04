<%@page import="kr.co.jboard1.vo.UserVo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String uid  = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	// 사용자 DB 조회
	UserVo user = null;
	
	try {
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
		
		Connection conn = ds.getConnection();
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)");
		psmt.setString(1, uid);
		psmt.setString(2, pass);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()) { // rs.next가 참일때 실행됨 즉 회원일 경우에만 실행 됨
			user = new UserVo();
			user.setUid(rs.getString(1));
			user.setPass(rs.getString(2));
			user.setName(rs.getString(3));
			user.setNick(rs.getString(4));
			user.setEmail(rs.getString(5));
			user.setHp(rs.getString(6));
			user.setRole(rs.getString(7));
			user.setZip(rs.getString(8));
			user.setAddr1(rs.getString(9));
			user.setAddr2(rs.getString(10));
			user.setRegip(rs.getString(11));
			user.setRegDate(rs.getString(12));
			user.setLeaveDate(rs.getString(13));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}

	// 회원 여부 확인 - 세션처리
	if(user != null){ // 회원이 맞을 경우 즉 rs.next가 참이여서 new UserVO가 생성됐을 경우
		session.setAttribute("sessUser", user);
		
		// 리다이렉트
		response.sendRedirect("/Jboard1/list.jsp");
	}else { // 회원이 아닐 경우
		response.sendRedirect("/Jboard1/user/login.jsp?success=100");
	}
	
	
	// 리다이렉트
	

%>