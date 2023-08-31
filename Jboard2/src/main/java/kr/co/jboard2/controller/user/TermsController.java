package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.TermsDTO;
import kr.co.jboard2.service.TermsService;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet {
	
	private static final long serialVersionUID = -2522080502790051830L;
	private TermsService service = new TermsService();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("TermsController doGet()...1");
		
		TermsDTO terms = service.selectTerms();
		req.setAttribute("terms", terms);
		
		logger.debug("terms : " + terms);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
}
