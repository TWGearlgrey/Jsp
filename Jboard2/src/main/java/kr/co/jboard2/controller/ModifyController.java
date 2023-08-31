package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = -8989721235114878879L;
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

		if(sessUser != null) {
			
			logger.info("ModifyController doGet()...1");
			
			String no = req.getParameter("no");
			ArticleDTO article = aService.selectArticle(no);
			req.setAttribute("article", article);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("ModifyController doPost()...1");
		
		MultipartRequest mr = aService.uploadFile(req);
		
		String no      = mr.getParameter("no");
		String title   = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oName   = mr.getOriginalFileName("file");
		
		logger.debug("no : " + no);
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("oName : " + oName);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setNo(no);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		
		aService.updateArticle(dto);
		
		// 파일명 수정 및 파일 테이블 Insert
		if(oName != null) {

			String sName = aService.renameToFile(req, oName);
			
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oName);
			fileDto.setNewName(sName);
			
			fService.insertFile(fileDto);
		}
		
		resp.sendRedirect("/Jboard2/view.do?no="+no);
	}
}