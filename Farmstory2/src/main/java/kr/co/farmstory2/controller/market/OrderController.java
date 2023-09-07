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

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = -764008782917500862L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doPost()...1");
		String thumb2     = req.getParameter("thumb2");
		String pName      = req.getParameter("pName");
		String pNo        = req.getParameter("pNo");
		String delivery   = req.getParameter("delivery");
		String price      = req.getParameter("price");
		String count      = req.getParameter("count");
		String total      = req.getParameter("total");
		String finalPrice = req.getParameter("final");
		
		logger.debug("thumb2 : " + thumb2);
		logger.debug("pName : " + pName);
		logger.debug("pNo : " + pNo);
		logger.debug("delivery : " + delivery);
		logger.debug("deliveryWithComma : " + Utils.comma(delivery)+'원');
		logger.debug("price : " + price);
		logger.debug("count : " + count);
		logger.debug("total : " + total);
		logger.debug("finalPrice : " + finalPrice);
		
		req.setAttribute("thumb2", thumb2);
		req.setAttribute("pName", pName);
		req.setAttribute("pNo", pNo);
		req.setAttribute("delivery", delivery);
		req.setAttribute("deliveryWithComma", Utils.comma(delivery)+'원');
		req.setAttribute("price", price);
		req.setAttribute("priceWithComma", Utils.comma(price));
		req.setAttribute("count", count);
		req.setAttribute("total", total);
		req.setAttribute("totalWithComma", Utils.comma(total));
		req.setAttribute("finalPrice", finalPrice);
		req.setAttribute("finalPriceWithComma", Utils.comma(finalPrice));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/order.jsp");
		dispatcher.forward(req, resp);	
	}
}