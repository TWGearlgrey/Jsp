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

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = -2120022050925682570L;
	private ArticleService service = ArticleService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
			String no = req.getParameter("no");
			
			logger.debug("no : " + no);
			
			// 글 조회
			ArticleDTO article = service.selectArticle(no);
			
			// 댓글 조회
			List<ArticleDTO> comments = service.selectComments(no);
			
			// View 공유 참조
			req.setAttribute("no", no);
			req.setAttribute("article", article);
			req.setAttribute("comments", comments);
			
			logger.debug("no : " + no);
			logger.debug("article : " + article);
			logger.debug("comments : " + comments);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("ViewController doPost()...1");
		
		String no      = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer  = req.getParameter("writer");
		String regip   = req.getRemoteAddr();
		
		logger.debug("no : " + no);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(no);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		logger.debug("dto : " + dto); 
		
		// 댓글 등록
		service.insertComment(dto);
		
		// 댓글 카운트 ++;
		service.updateArticleForComment(no);
		
		
		resp.sendRedirect("/Jboard2/view.do?no="+no);
	}
}
