package kr.co.farmstory2.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet {

	private static final long serialVersionUID = -7282661194074999566L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		
		// 파일 업로드
		MultipartRequest mr = service.uploadFile(req);
		
		String seller   = mr.getParameter("seller");
		String pName    = mr.getParameter("pName");
		String type     = mr.getParameter("type");
		String price    = mr.getParameter("price");
		String delivery = mr.getParameter("delivery");
		String stock    = mr.getParameter("stock");
		String thumb1   = mr.getOriginalFileName("thumb1");
		String thumb2   = mr.getOriginalFileName("thumb2");
		String thumb3   = mr.getOriginalFileName("thumb3");
		String etc      = mr.getParameter("etc");
		String path     = service.getFilePath(req);
		
		logger.debug("seller   : " + seller);
		logger.debug("pName    : " + pName);
		logger.debug("type     : " + type);
		logger.debug("price    : " + price);
		logger.debug("delivery : " + delivery);
		logger.debug("stock    : " + stock);
		logger.debug("thumb1   : " + thumb1);
		logger.debug("thumb2   : " + thumb2);
		logger.debug("thumb3   : " + thumb3);
		logger.debug("etc      : " + etc);
		logger.debug("path     : " + path);
		
		ProductDTO dto = new ProductDTO(path);
		dto.setType(type);
		dto.setpName(pName);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setThumb1ForRename(thumb1);
		dto.setThumb2ForRename(thumb2);
		dto.setThumb3ForRename(thumb3);
		dto.setSeller(seller);
		dto.setEtc(etc);
		
		logger.debug("dto : " + dto);
		
		service.insertProduct(dto);
		
		resp.sendRedirect("/Farmstory2/admin/productRegister.do");
	}
}