<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no     = request.getParameter("no");
	String parent = request.getParameter("parent");
	
	ArticleDAO dao = new ArticleDAO();
	
	// 댓글 삭제
	dao.deleteComment(no);
	
	// 댓글 카운트 수정( comment --; )
	dao.deleteArticleForComment(parent);
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
%>