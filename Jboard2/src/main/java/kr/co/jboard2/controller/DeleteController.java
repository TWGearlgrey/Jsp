package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -9040060042780306760L;
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		if(sessUser != null) {
		
			logger.info("doGet()...1");
	
			// 글 번호 수신
			String no = req.getParameter("no");
			logger.debug("no : " + no);
			
			// 파일 삭제(DB)
			int result = fService.deleteFile(no);
			logger.info("doGet()...2-1 file DB delete...");
			
			// 파일 삭제(Directory)
			if(result > 0) {
				logger.info("doGet()...2-2 file dir delete...");
				
				String path = aService.getFilePath(req);
				File file = new File(path + "/" + "파일명");
				
				logger.debug("path : " + path);
				logger.debug("file : " + file);
				
				if(file.exists()) {
					file.delete();
				}
				
				logger.info("doGet()...2-3 file dir delete end");
			}else {
				logger.info("doGet()...2-end file is null");
			}
			
			// 게시글+댓글 삭제
			aService.deleteArticle(no);
			logger.info("doGet()...3");
			
			resp.sendRedirect("/Jboard2/list.do");
		
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
}
