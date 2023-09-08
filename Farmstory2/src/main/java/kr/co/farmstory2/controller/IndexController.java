package kr.co.farmstory2.controller;

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

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

// 시작페이지 index.jsp가 없기 때문에 기본주소("")에 대한 맵핑 추가
@WebServlet(value = {"", "/index.do"})
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = -7872591534348984559L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		List<ArticleDTO> grows   = service.latestsArticles("grow",   5);
		List<ArticleDTO> schools = service.latestsArticles("school", 5);
		List<ArticleDTO> storys  = service.latestsArticles("story",  5);
		
		List<ArticleDTO> notices = service.latestsArticles("notice", 3);
		List<ArticleDTO> qnas    = service.latestsArticles("qna",    3);
		List<ArticleDTO> faqs    = service.latestsArticles("faq",    3);
		
		logger.debug("grows   : " + grows.subList(4, 5));
		logger.debug("schools : " + schools.subList(4, 5));
		logger.debug("storys  : " + storys.subList(4, 5));
		logger.debug("notices : " + notices.subList(2, 3));
		logger.debug("qnas    : " + qnas.subList(2, 3));
		logger.debug("faqs    : " + faqs.subList(2, 3));
		
		req.setAttribute("grows",   grows);
		req.setAttribute("schools", schools);
		req.setAttribute("storys",  storys);
		
		req.setAttribute("notices", notices);
		req.setAttribute("qnas",    qnas);
		req.setAttribute("faqs",    faqs);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);	
	}
}