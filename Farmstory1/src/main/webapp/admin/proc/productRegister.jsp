<%@page import="java.io.File"%>
<%@page import="java.util.UUID"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.farmstory1.dto.ProductDTO"%>
<%@page import="kr.farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 파일 폼 데이터 수신
	String path = application.getRealPath("/thumb");
	int maxSize = 1024 * 1024 * 10;
	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());

	// 일반 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String productName = mr.getParameter("productName");
	String type        = mr.getParameter("type");
	String price       = mr.getParameter("price");
	String delivery    = mr.getParameter("delivery");
	String stock       = mr.getParameter("stock");
	String thumb1      = mr.getOriginalFileName("thumb1");
	String thumb2      = mr.getOriginalFileName("thumb2");
	String thumb3      = mr.getOriginalFileName("thumb3");
	String seller      = mr.getParameter("seller");
	String etc         = mr.getParameter("etc");
	
	// 데이터 처리
	ProductDTO dto = new ProductDTO(path);
	dto.setpName(productName);
	dto.setType(type);
	dto.setPrice(price);
	dto.setDelivery(delivery);
	dto.setStock(stock);
	dto.setThumb1(thumb1);
	dto.setThumb2(thumb2);
	dto.setThumb3(thumb3);
	dto.setSeller(seller);
	dto.setEtc(etc);
	
	ProductDAO dao = new ProductDAO();
	dao.insertProduct(dto);
	
	response.sendRedirect("../productRegister.jsp");
%>