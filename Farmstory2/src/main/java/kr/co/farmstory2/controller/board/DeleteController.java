package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = -4550516370748336927L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...1");
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String no    = req.getParameter("no");
		logger.debug("no    : " + no);
		logger.debug("group : " + group);
		logger.debug("cate  : " + cate);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("no", no);
		
		ArticleDTO dto = aService.selectArticle(no);
		String fileName = dto.getFileDto().getNewName();

		// 파일 삭제(DB)
		int result = fService.deleteFile(no);
		logger.info("DELETE FILE DATABASE...");
		
		// 파일 삭제(Directory)
		if(result > 0) {
			logger.debug("result : " + result);
			logger.info("DELETE FILE DIRECTORY...");
			
			String path = aService.getFilePath(req);
			
			logger.debug("oriName : " + fileName);
			
			File file = new File(path+"/"+fileName); 
			logger.debug("file : " + file);
			
			if(file.exists()) {
				file.delete();
				logger.info("DELETE COMPLETE!");
			}
				
		}else {
			logger.info("DELETE FAILED:: reason FILE IS NULL.");
		}
		
		// 게시글+댓글 삭제
		aService.deleteArticle(no);
		logger.info("DELETE ARTICLE...");
		
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}
}
