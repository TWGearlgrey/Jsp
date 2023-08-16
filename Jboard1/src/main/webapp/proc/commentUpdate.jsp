<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no      = request.getParameter("no");
	String comment = request.getParameter("comment");
	String parent  = request.getParameter("parent");
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateComment(no, comment);
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
%>