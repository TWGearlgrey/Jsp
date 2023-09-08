package kr.co.farmstory2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {
	
	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductDAO dao = new ProductDAO();

	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}

	public ProductDTO selectProduct(String pNo) {
		return dao.selectProduct(pNo);
	}
	
	public List<ProductDTO> selectProducts(int start) {
		return dao.selectProducts(start);
	}
	
	public List<ProductDTO> selectProducts(String type, int start) {
		return dao.selectProducts(type, start);
	}
	
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	
	public int deleteProduct(String pNo) {
		return dao.deleteProduct(pNo);
	}
	
	// ────────────────────────────────────────────────────────────────┘
	
	// 추가 // ─────────────────────────────────────────────────────────┐
	public int selectCountProductsTotal() {
		return dao.selectCountProductsTotal();
	}
	
	public int selectCountProductsTotal(String type) {
		return dao.selectCountProductsTotal(type);
	}
	
	
	// thumb 업로드 구현 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public String getFilePath(HttpServletRequest req) {
		
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/thumb");
		
		return path;
	}
	
	
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

	
	// 리스트 페이징 구현 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total / 10 + 1;
		}
		
		return lastPageNum;
	}
	
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start + 1;
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null){
			currentPage = Integer.parseInt(pg);	
		}
		return currentPage;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
}
