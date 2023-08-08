<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.jboard1.db.SQL"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
//// 흐름 : list -> write -> writeProc -> DB전달/ -> list
	
	// 전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String title   = request.getParameter("title");
	String content = request.getParameter("content");
	String file    = request.getParameter("file");
	String writer  = request.getParameter("writer");
	String regip   = request.getRemoteAddr();
	
	ArticleDTO vo = new ArticleDTO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setRegip(regip);
	
	// 데이터베이스 처리
	ArticleDAO dao = new ArticleDAO();
	dao.insertArticle(vo);
	
	// 리다이렉트
	response.sendRedirect("/Jboard1/list.jsp");
%>