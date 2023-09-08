package kr.co.farmstory2.controller.admin;

import java.io.File;
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
		String success = req.getParameter("success");
		if(success == null) {
			success = "0";
		}
		logger.debug("success : " + success);
		
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
		req.setAttribute("success", success);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		
		logger.debug("products       : " + products);
		logger.debug("currentPage    : " + currentPage);
		logger.debug("lastPageNum    : " + lastPageNum);
		logger.debug("pageGroupStart : " + result[0]);
		logger.debug("pageGroupEnd   : " + result[1]);
		logger.debug("pageStartNum   : " + pageStartNum);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		int count = 0;
		
		String[] chks = req.getParameterValues("chk_prod");
		String[] thumb1 = req.getParameterValues("thumb1");
		String[] thumb2 = req.getParameterValues("thumb2");
		String[] thumb3 = req.getParameterValues("thumb3");
		logger.debug("chk_prod : " + chks.toString());
		logger.debug("thumb1   : " + thumb1.toString());
		logger.debug("thumb2   : " + thumb2.toString());
		logger.debug("thumb3   : " + thumb3.toString());
		
		for(String pNo : chks) {
			int result = service.deleteProduct(pNo);
			logger.debug("delete order(count:"+ count +") pNo : " + pNo);
			logger.debug("result : " +result);
			
			if(result > 0) { // ProductList(DB)가 삭제된다면.

				// thumb 폴더에 있는 사진도 삭제...
				String path = service.getFilePath(req);
				
				File file1 = new File(path+"/"+thumb1[count]);
				File file2 = new File(path+"/"+thumb2[count]);
				File file3 = new File(path+"/"+thumb3[count]);
				
				logger.debug("count : " + count);
				logger.debug("file1 : " + file1);
				logger.debug("file1 : " + file2);
				logger.debug("file1 : " + file3);
				
				if(file1.exists()) {
					file1.delete();
					logger.debug("fifle1 DELETE");
				}
				
				if(file2.exists()) {
					file2.delete();
					logger.debug("fifle2 DELETE");
				}
				
				if(file3.exists()) {
					file3.delete();
					logger.debug("fifle3 DELETE");
				}
				
				count++;
				logger.debug("count : " + count);
				
			}else {
				logger.info("DELETE FAILED:: reason FILE IS NULL.");
			}
			
		}
		
		resp.sendRedirect("/Farmstory2/admin/productList.do?success=200");
	
	}
}