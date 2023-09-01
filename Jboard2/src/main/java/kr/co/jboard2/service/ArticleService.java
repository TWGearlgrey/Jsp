package kr.co.jboard2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public enum ArticleService {

	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public void insertComment(ArticleDTO dto) {
		dao.insertComment(dto);
	}
	
	public List<ArticleDTO> selectComments(String no) {
		return dao.selectComments(no);
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	public void updateArticleForComment(String no) {
		dao.updateArticleForComment(no);
	}
	
	
	// 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		logger.debug("oName : " + oName);
		logger.debug("sName : " + sName);
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		logger.debug("f1 : " + f1);
		logger.debug("f2 : " + f2);
		
		// 파일명 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 경로구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, 
									  path, 
									  maxSize, 
									  "UTF-8", 
									  new DefaultFileRenamePolicy());
			
		} catch (IOException e) {
			logger.error("uploadFile error : " + e.getMessage());
		}
		
		return mr;
	}
	
	// 파일 다운로드
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
		// response 파일 다운로드 헤더 수정 (response : server에서 client로 넘어가는 전송 객체) 
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 파일 스트림 작업
		String path = getFilePath(req);
		File file = new File(path + "/" + dto.getNewName());
		
		BufferedInputStream  bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		while(true){
			int data = bis.read();
			if(data == -1) {
				break;
			}
			
			bos.write(data);
		}
		bis.close();
		bos.close();
	}
}