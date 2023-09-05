package kr.co.farmstory2.controller.board;

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

@WebServlet("/board/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 7165514476577189368L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");

		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String pg    = req.getParameter("pg");
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountTotal(cate);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 현재 페이지 게시물 조회
		List<ArticleDTO> articles = service.selectArticles(cate, start);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("articles", articles);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum);
		
		logger.debug("group          : " + group);
		logger.debug("cate           : " + cate);
		logger.debug("articles       : " + articles);
		logger.debug("currentPage    : " + currentPage);
		logger.debug("lastPageNum    : " + lastPageNum);
		logger.debug("pageGroupStart : " + result[0]);
		logger.debug("pageGroupEnd   : " + result[1]);
		logger.debug("pageStartNum   : " + pageStartNum);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/list.jsp");
		dispatcher.forward(req, resp);	
	}
}