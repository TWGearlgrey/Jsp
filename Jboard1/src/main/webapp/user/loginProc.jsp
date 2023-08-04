<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="kr.co.jboard1.vo.UserVO"%>
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
	UserVO user = UserDAO.getInstance().selectUser(uid, pass);
	
	response.sendRedirect("/Jboard1/user/login.jsp");

	// 회원 여부 확인 - 세션처리
	if(user != null){ // 회원이 맞을 경우 즉 rs.next가 참이여서 new UserVO가 생성됐을 경우
		// 사용자 세션 저장
		session.setAttribute("sessUser", user);
		
		// 리다이렉트
		response.sendRedirect("/Jboard1/list.jsp");
		
	}else { // 회원이 아닐 경우
		// 리다이렉트
		response.sendRedirect("/Jboard1/user/login.jsp?success=100");
	}
%>