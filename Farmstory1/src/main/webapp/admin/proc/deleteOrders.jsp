<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String[] chks = request.getParameterValues("chk");
	
	OrderDAO dao = new OrderDAO();
	
	for(String order : chks) {
		dao.deleteOrder(order);
	}
	
	response.sendRedirect("../orderList.jsp");
%>