package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet {

	private static final long serialVersionUID = 7686345524627400840L;
	private ArticleService service = ArticleService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
			
			logger.info("doGet()...1");
			
			String kind   = req.getParameter("kind");
			String no     = req.getParameter("no");
			String parent = req.getParameter("parent");
			
			logger.debug("kind : " + kind);
			logger.debug("no : " + no);
			logger.debug("parent : " + parent);
			
			int result = 0;
			int currentComment = 0;
			
			switch(kind) {
				case "REMOVE":
					result = service.deleteComment(no);
					logger.info("doGet()...2 :: DELETE COMMENT : " + result);
					
					if(result > 0) {
						service.deleteArticleForComment(parent);
						logger.info("doGet()...3 :: COMMENT COUNT--;");
						currentComment = service.currentCommentsCount(parent);
						logger.info("doGet()...4 :: CURRENT COMMENTS : " + currentComment);
					}
					
					break;
			}
			
			// JSON 출력
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			json.addProperty("currentComment", currentComment);
			resp.getWriter().print(json);
		
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		
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
		int result = service.insertComment(dto);
		logger.info("doPost()...2 :: INSERT COMMENT");
		
		// 댓글 카운트 ++;
		if(result > 0) {
			service.updateArticleForComment(no);
			logger.info("doPost()...3 :: COMMENT COUNT++;");
		}
		
		// 작성한 댓글 번호 확인
		
		
		// 리다이렉트(폼 전송). 하지만 json이므로 json 출력을 해야 한다.
		//resp.sendRedirect("/Jboard2/view.do?no="+no);
		
		// Json 출력(AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
}
