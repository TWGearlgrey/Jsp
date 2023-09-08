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

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = -5045259794094966371L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 게시글 수정 페이지 데이터 불러오기
		logger.info("doGet()...1");
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String no    = req.getParameter("no");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		ArticleDTO article = aService.selectArticle(no);
		req.setAttribute("article", article);
		logger.debug("article : " + article);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/modify.jsp");
		dispatcher.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 게시글 수정 완료
		MultipartRequest mr = aService.uploadFile(req);
		
		String group   = mr.getParameter("group");
		String cate    = mr.getParameter("cate");
		String title   = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oriName = mr.getOriginalFileName("file");
		String no      = mr.getParameter("no");
		String writer  = mr.getParameter("writer");
		
		logger.debug("no    : " + no);
		logger.debug("group : " + group);
		logger.debug("cate  : " + cate);

		logger.debug("title   : " + title);
		logger.debug("content : " + content);
		logger.debug("file    : " + oriName);
		logger.debug("writer  : " + writer);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setNo(no);
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oriName);
		
		logger.debug("dto : " + dto);
		
		// DB update
		aService.updateArticle(dto);
		
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
		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
		
	}
}