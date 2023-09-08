package kr.co.farmstory2.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.service.OrderService;

@WebServlet("/admin/orderList.do")
public class OrderListController extends HttpServlet {

	private static final long serialVersionUID = 1324357519406568720L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService service = OrderService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		String pg = req.getParameter("pg");
		String success = req.getParameter("success");
		if(success == null) {
			success = "0";
		}
		logger.debug("success : " + success);
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountOrdersTotal();
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 오더리스트 호출
		List<OrderDTO> orders = service.selectOrders(start);
		
		req.setAttribute("orders", orders);
		req.setAttribute("success", success);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		
		logger.debug("orders         : " + orders);
		logger.debug("currentPage    : " + currentPage);
		logger.debug("lastPageNum    : " + lastPageNum);
		logger.debug("pageGroupStart : " + result[0]);
		logger.debug("pageGroupEnd   : " + result[1]);
		logger.debug("pageStartNum   : " + pageStartNum);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/orderList.jsp");
		dispatcher.forward(req, resp);	
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		int count = 1;
		
		String[] chks = req.getParameterValues("chk_order");
		
		for(String orderNo : chks) {
			service.deleteOrder(orderNo);
			logger.debug("delete order(count:"+ count++ +") orderNo : " + orderNo);
		}
		
		resp.sendRedirect("/Farmstory2/admin/orderList.do?success=200");
	}
}