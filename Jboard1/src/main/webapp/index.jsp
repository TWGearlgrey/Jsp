<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 현재 로그인 여부를 확인
	UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

	if(sessUser == null){
		pageContext.forward("./user/login.jsp");
		
	}else {
		pageContext.forward("./list.jsp");
		
	}
%>