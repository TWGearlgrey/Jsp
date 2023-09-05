package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/fileDownload.do")
public class FileDownloadController extends HttpServlet {

	private static final long serialVersionUID = 2608153774921702065L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService    fService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info("doGet()...1");
		
		// 데이터 수신
		String fno = req.getParameter("fno");
		logger.debug("fno : " + fno);
		
		// 파일 조회
		FileDTO fileDto =  fService.selectFile(fno);
		logger.debug("fileDto : " + fileDto);
		
		// 파일 다운로드
		aService.downloadFile(req, resp, fileDto);
		logger.info("doGet()...2");
		
	}
}
