<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 로그인 여부를 확인
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");

	if(sessUser == null){
		pageContext.forward("./user/login.jsp");
		
	}else {
		pageContext.forward("./list.jsp");
		
	}
%>