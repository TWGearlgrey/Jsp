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
import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.OrderService;
import kr.co.farmstory2.service.ProductService;
import kr.co.farmstory2.service.UserService;

@WebServlet(value = {"/admin", "/admin/index.do"})
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = -5141473195026626452L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService pService = ProductService.INSTANCE;
	private OrderService oService = OrderService.INSTANCE;
	private UserService uService = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");

		List<ProductDTO> products = pService.selectProducts(0);
		List<OrderDTO> orders = oService.selectOrders(0);
		List<UserDTO> users = uService.selectUsers(0);

		req.setAttribute("products", products);
		req.setAttribute("orders", orders);
		req.setAttribute("users", users);
		
		logger.debug("products : " + products);
		logger.debug("orders   : " + orders);
		logger.debug("users    : " + users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/index.jsp");
		dispatcher.forward(req, resp);
	}

}
