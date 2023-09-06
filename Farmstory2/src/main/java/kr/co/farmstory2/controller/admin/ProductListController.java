package kr.co.farmstory2.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productList.do")
public class ProductListController extends HttpServlet {

	private static final long serialVersionUID = 674836839312349781L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		String pg = req.getParameter("pg");
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountProductsTotal();
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		List<ProductDTO> products = service.selectProducts(start);
		req.setAttribute("products", products);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		
		logger.debug("products       : " + products.subList(0, 1));
		logger.debug("currentPage    : " + currentPage);
		logger.debug("lastPageNum    : " + lastPageNum);
		logger.debug("pageGroupStart : " + result[0]);
		logger.debug("pageGroupEnd   : " + result[1]);
		logger.debug("pageStartNum   : " + pageStartNum);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);	
	}
}