package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 7787169292569437228L;
	private ArticleService service = ArticleService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
			
			logger.info("ListController doGet()...1");
			
			// 데이터 수신
			String pg     = req.getParameter("pg");
			String search = req.getParameter("search");
			
			// 현재 페이지 번호
			int currentPage = service.getCurrentPage(pg);
			
			// 전체 게시물 갯수 
			int total = service.selectCountTotal(search);
			
			// 마지막 페이지 번호
			int lastPageNum = service.getLastPageNum(total);
			
			// 페이지 그룹 start, end 번호
			int[] result = service.getPageGroupNum(currentPage, lastPageNum);
			
			// 페이지 시작번호
			int pageStartNum = service.getPageStartNum(total, currentPage);
			
			// 시작 인덱스
			int start = service.getStartNum(currentPage);
			
			// 글 조회
			List<ArticleDTO> articles = service.selectArticles(start, search);
			req.setAttribute("articles", articles);
			
			req.setAttribute("pageStartNum", pageStartNum);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("pageGroupStart", result[0]);
			req.setAttribute("pageGroupEnd", result[1]);
			req.setAttribute("search", search);
			
			logger.debug("total : " + total);
			logger.debug("start : " + start);
			logger.debug("lastPageNum : " + lastPageNum);
			logger.debug("pageStartNum : " + pageStartNum);
			logger.debug("pageGroupEnd : " + result[1]);
			logger.debug("pageGroupStart : " + result[0]);
			logger.debug("currentPage : " + currentPage);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
}
