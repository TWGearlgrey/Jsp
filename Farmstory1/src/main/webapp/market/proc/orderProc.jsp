<%@page import="kr.farmstory1.dto.OrderDTO"%>
<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String pNo      = request.getParameter("pNo");
	String count    = request.getParameter("count");
	String delivery = request.getParameter("delivery");
	String price    = request.getParameter("price");
	String total    = request.getParameter("final");
	String uid      = request.getParameter("uid");
	
	String buyer = request.getParameter("buyer");
	String hp    = request.getParameter("hp");
	String zip   = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String etc   = request.getParameter("etc");

	// dto 생성
	OrderDTO dto = new OrderDTO();
	dto.setOrderProduct(pNo);
	dto.setOrderCount(count);
	dto.setOrderDelivery(delivery);
	dto.setOrderPrice(price);
	dto.setOrderTotal(total);
	dto.setReceiver(buyer);
	dto.setHp(hp);
	dto.setZip(zip);
	dto.setAddr1(addr1);
	dto.setAddr2(addr2);
	dto.setOrderEtc(etc);
	dto.setOrderUser(uid);

	// 데이터 처리
	OrderDAO dao = new OrderDAO();
	dao.insertOrder(dto);
	
	response.sendRedirect("/Farmstory1/market/list.jsp");
%>