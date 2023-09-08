package kr.co.farmstory2.controller.market;

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

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -3043407315367193492L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("market > doGet()...1");

		String type    = req.getParameter("type");
		String pg      = req.getParameter("pg");
		String no      = req.getParameter("no");
		String success = req.getParameter("success");
		
		logger.debug("type    : " + type + "(NULL IS '0')");
		logger.debug("pg      : " + pg);
		logger.debug("no      : " + no);
		logger.debug("success : " + success);
		
		req.setAttribute("no", no);
		req.setAttribute("success", success);
		
		if(type == null){
			type = "0";
		}
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountProductsTotal(type);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		List<ProductDTO> products = service.selectProducts(type, start);
		req.setAttribute("products", products);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("total", total);
		req.setAttribute("type", type);
		
		logger.debug("products       : " + products);
		logger.debug("currentPage    : " + currentPage);
		logger.debug("lastPageNum    : " + lastPageNum);
		logger.debug("pageGroupStart : " + result[0]);
		logger.debug("pageGroupEnd   : " + result[1]);
		logger.debug("pageStartNum   : " + pageStartNum);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/list.jsp");
		dispatcher.forward(req, resp);	
	}
}