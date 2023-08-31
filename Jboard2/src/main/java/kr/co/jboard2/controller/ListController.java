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
			
			String pg = req.getParameter("pg");
			
			// DAO 객체 생성
			ArticleDAO dao = ArticleDAO.getInstance();
			
			// 페이지 관련 변수
			int start = 0;
			int currentPage = 1;
			int total = 0;
			int lastPageNum = 0;
			
			int pageGroupCurrent = 1;
			int pageGroupStart = 1;
			int pageGroupEnd = 0;
			
			int pageStartNum = 0;
			
			// 현재 페이지 계산
			if(pg != null) {
				currentPage = Integer.parseInt(pg);
			}
			
			// Limit 시작값 계산
			start = (currentPage -1) * 10;
			
			// 전체 게시물 갯수 조회
			total = dao.selectCountTotal();
			
			// 페이지 번호 계산
			if(total % 10 == 0) {
				lastPageNum = (total / 10);
			}else {
				lastPageNum = (total / 10) + 1;
			}
			
			// 페이지 그룹 계산
			pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
			pageGroupStart   = (pageGroupCurrent - 1) * 10 + 1;
			pageGroupEnd     = pageGroupCurrent * 10;
			
			if(pageGroupEnd > lastPageNum) {
				pageGroupEnd = lastPageNum;
			}
			
			// 페이지 시작번호 계산
			pageStartNum = total - start + 1;
			
			req.setAttribute("pageStartNum", pageStartNum);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("pageGroupStart", pageGroupStart);
			req.setAttribute("pageGroupEnd", pageGroupEnd);
			
			logger.debug("total : " + total);
			logger.debug("start : " + start);
			logger.debug("lastPageNum : " + lastPageNum);
			logger.debug("pageStartNum : " + pageStartNum);
			logger.debug("pageGroupEnd : " + pageGroupEnd);
			logger.debug("pageGroupStart : " + pageGroupStart);
			logger.debug("pageGroupCurrent : " + pageGroupCurrent);
			logger.debug("currentPage : " + currentPage);
			
			// 현재 페이지 게시물 조회
			List<ArticleDTO> articles = service.selectArticles(start);
			req.setAttribute("articles", articles);
			
			logger.debug("articles : " + articles);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
}
