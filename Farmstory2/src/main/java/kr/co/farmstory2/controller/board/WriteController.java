package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;


@WebServlet("/board/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = -550554488397185817L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FileService fService = FileService.INSTANCE;
	private ArticleService aService = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...1");
		
		// 파일 업로드 
		MultipartRequest mr = aService.uploadFile(req);

		String group   =  mr.getParameter("group");
		String cate    =  mr.getParameter("cate");
		String title   =  mr.getParameter("title");
		String content =  mr.getParameter("content");
		String oriName =  mr.getOriginalFileName("file");
		String writer  =  mr.getParameter("writer");
		String regip   = req.getRemoteAddr();
		
		logger.debug("group   : " + group);
		logger.debug("cate    : " + cate);
		
		logger.debug("title   : " + title);
		logger.debug("content : " + content);
		logger.debug("oriName : " + oriName);
		logger.debug("writer  : " + writer);
		logger.debug("regip   : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oriName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		logger.debug("dto : " + dto);
		
		// DB insert
		int no = aService.insertArticle(dto);
		
		// 파일명 수정 및 파일 테이블 insert
		if(oriName != null) {
			
			String newName = aService.renameToFile(req, oriName);
			
			// 파일 테이블 insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oriName);
			fileDto.setNewName(newName);
			
			fService.insertFile(fileDto);
		}
		
		// Redirect
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}
}