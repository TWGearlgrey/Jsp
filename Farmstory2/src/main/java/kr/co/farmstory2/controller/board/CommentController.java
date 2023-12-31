package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet {

	private static final long serialVersionUID = 3096232538471515350L;
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");
		
		String kind   = req.getParameter("kind");
		String no     = req.getParameter("no");
		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		
		logger.debug("kind    : " + kind);
		logger.debug("no      : " + no);
		logger.debug("parent  : " + parent);
		logger.debug("content : " + content);
		
		int result = 0;
		int currentComment = 0;
		
		switch(kind) {
			case "REMOVE": 
				result = service.deleteComment(no);
				logger.info("doGet()...2-1 :: DELETE COMMENT : " + result);
				
				if(result > 0) {
					service.updateCommentCount(parent);
					logger.info("doGet()...2-2 :: COMMENT COUNT");
					
					currentComment = service.currentCommentsCount(parent);
					logger.info("doGet()...2-3 :: CURRENT COMMENTS : " + currentComment);
				}
				logger.info("doGet()...2-4 :: DELETE END...");
				break;
				
				
			case "MODIFY":
				result = service.updataComment(content, no);
				logger.info("doGet...3-1 :: COMMENT MODIFY...");
				logger.info("doGet...3-1 :: MODIFY END...");
				break;
		}
		
		// JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("currentComment", currentComment);
		resp.getWriter().print(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doPost()...1");
		
		String no      = req.getParameter("parent");
		String writer  = req.getParameter("writer");
		String content = req.getParameter("content");
		String regip   = req.getRemoteAddr();
		
		logger.debug("no      : " + no);
		logger.debug("writer  : " + writer);
		logger.debug("content : " + content);
		logger.debug("regip   : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(no);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		logger.debug("dto : " + dto);
		
		// 댓글 등록
		int[] result = service.insertComment(dto);
		logger.info("doPost()...2 :: INSERT COMMENT result : " + result[0]);
		
		// 댓글 카운트 ++;
		if(result[0] > 0) {
			service.updateCommentCount(no);
			logger.info("doPost()...3 :: COMMENT COUNT");
		}
		
		// Json 출력(ajax 요청에 응답)
		JsonObject json = new JsonObject();
		json.addProperty("result", result[0]);
		json.addProperty("no", result[1]);
		resp.getWriter().print(json);
	}
}
