package kr.co.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.Utils;
import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.service.OrderService;

@WebServlet("/market/orderComplete.do")
public class OrderCompleteController extends HttpServlet {

	private static final long serialVersionUID = -764008782917500862L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService service = OrderService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doPost()...1");
		String orderProduct  = req.getParameter("orderProduct");
		String orderCount    = req.getParameter("orderCount");
		String orderDelivery = req.getParameter("orderDelivery");
		String orderPrice    = req.getParameter("orderPrice");
		String orderTotal    = req.getParameter("orderTotal");
		String orderUser     = req.getParameter("orderUser");
		String receiver      = req.getParameter("receiver");
		String hp            = req.getParameter("hp");
		String zip           = req.getParameter("zip");
		String addr1         = req.getParameter("addr1");
		String addr2         = req.getParameter("addr2");
		String orderEtc      = req.getParameter("orderEtc");
		
		logger.debug("orderProduct  : " + orderProduct);
		logger.debug("orderCount    : " + orderCount);
		logger.debug("orderDelivery : " + orderDelivery);
		logger.debug("orderPrice    : " + orderPrice);
		logger.debug("orderTotal    : " + orderTotal);
		logger.debug("orderUser     : " + orderUser);
		logger.debug("receiver      : " + receiver);
		logger.debug("hp            : " + hp);
		logger.debug("zip           : " + zip);
		logger.debug("addr1         : " + addr1);
		logger.debug("addr2         : " + addr2);
		logger.debug("orderEtc      : " + orderEtc);
		
		OrderDTO dto = new OrderDTO();
		dto.setOrderProduct(orderProduct);
		dto.setOrderCount(orderCount);
		dto.setOrderDelivery(orderDelivery);
		dto.setOrderPrice(orderPrice);
		dto.setOrderTotal(orderTotal);
		dto.setOrderUser(orderUser);
		dto.setReceiver(receiver);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setOrderEtc(orderEtc);
		
		logger.debug("dto : " + dto);
		
		int no = service.insertOrder(dto);
		logger.debug("no : " + no);
		
		resp.sendRedirect("/Farmstory2/market/list.do?success=200&no="+no);
	}
}