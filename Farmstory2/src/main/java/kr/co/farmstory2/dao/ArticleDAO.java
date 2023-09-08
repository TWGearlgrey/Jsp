package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;

public class ArticleDAO extends DBHelper{

	// 싱글톤 
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	// 로거
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	// CRUD 메서드 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	// 게시글 작성
	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		try {
			logger.info("insertArticle() start");
			
			conn = getConnection();
			conn.setAutoCommit(false); // Transaction 시작
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			logger.debug("rs : " + rs);
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
			logger.info("insertArticle() try end...(no: " + no + ")");
			
		} catch (Exception e) {
			logger.error("insertArticle() ERROR : " + e.getMessage());
		}
		return no;
	}
	
	// 게시글 view
	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		try {
			logger.info("selectArticle() start");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOriName(rs.getString(14));
				fileDto.setNewName(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				
				dto.setFileDto(fileDto);
			}
			close();
			logger.info("selectArticle() try end...");
			
		} catch (Exception e) {
			logger.error("selectArticle() ERROR : " + e.getMessage());
		}
		return dto;
	}
	
	// 게시글 list
	public List<ArticleDTO> selectArticles(String cate, int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			logger.info("selectArticles() start");
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTILCES);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
				
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTILCES_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
				psmt.setInt(3, start);
			}
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				articles.add(dto);
			}
			close();
			logger.info("selectArticles() try end...");
			
		}catch (Exception e) {
			logger.error("selectArticles() ERROR : " + e.getMessage());
		}
		return articles;
	}
	
	// 게시글 수정
	public void updateArticle(ArticleDTO dto) {
		try {
			logger.info("updateArticle() start");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setInt(4, dto.getNo());
			psmt.executeUpdate();
			close();
			logger.info("updateArticle() try end...");
			
		} catch (Exception e) {
			logger.error("updateArticle() ERROR : " + e.getMessage());
		}
	}
	
	// 게시글 삭제
	public void deleteArticle(String no) {
		try {
			logger.info("deleteArticle() start");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
			logger.info("deleteArticle() try end...");
			
		} catch (Exception e) {
			logger.error("deleteArticle() ERROR : " + e.getMessage());
		}
	}
	
	// 메인페이지 최신 게시글 view
	public List<ArticleDTO> latestsArticles(String cate, int num) {
		List<ArticleDTO> latests = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATESTS);
			psmt.setString(1, cate);
			psmt.setInt(2, num);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setRdate(rs.getString(4));
				latests.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("latestsArticles() ERROR : " + e.getMessage());
		}
		return latests;
	}
	
	
	
	
	// 댓글 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	// view 페이지에서 댓글 조회
		public List<ArticleDTO> selectComments(String parent) {
			
			List<ArticleDTO> comments = new ArrayList<>();
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
				psmt.setString(1, parent);
				
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					ArticleDTO dto = new ArticleDTO();
					dto.setNo(rs.getInt(1));
					dto.setParent(rs.getInt(2));
					dto.setComment(rs.getInt(3));
					dto.setCate(rs.getString(4));
					dto.setTitle(rs.getString(5));
					dto.setContent(rs.getString(6));
					dto.setFile(rs.getInt(7));
					dto.setHit(rs.getInt(8));
					dto.setWriter(rs.getString(9));
					dto.setRegip(rs.getString(10));
					dto.setRdate(rs.getString(11));
					dto.setNick(rs.getString(12));
					
					comments.add(dto);
				}
				close();
			}catch (Exception e) {
				logger.error("selectComments() ERROR : " + e.getMessage());
			}
			
			return comments;
		}
		
		
		// 덧글 작성 {성공유무, 댓글 no} 반환
		public int[] insertComment(ArticleDTO dto) {
			int[] result = {0, 0};
			try {
				conn = getConnection();
				conn.setAutoCommit(false); // Transaction 시작
				
				stmt = conn.createStatement();
				psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
				psmt.setInt(1, dto.getParent());
				psmt.setString(2, dto.getContent());
				psmt.setString(3, dto.getWriter());
				psmt.setString(4, dto.getRegip());
				result[0] = psmt.executeUpdate();
				rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
				conn.commit();
				
				if(rs.next()) {
					result[1] = rs.getInt(1);
				}
				logger.debug("result = {" + result[0] + ", " + result[1] + ")");
				
				close();
			}catch (Exception e) {
				logger.error("insertComment() ERROR : " + e.getMessage());
			}
			return result;
		}
		
		// 댓글 수정 ~~ 성공유무 반환
		public int updataComment(String content, String no) {
			int result = 0;
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
				psmt.setString(1, content);
				psmt.setString(2, no);
				result = psmt.executeUpdate();
				close();
				
			} catch (Exception e) {
				logger.error("updateComment ERROR : " + result);
			}
			return result;
		}
		
		
	
	// 추가 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	// 페이징을 위한 게시글 수 조회
	public int selectCountTotal(String cate, String search) {
		
		int total = 0;
		
		try {
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
			}
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();			
		}catch (Exception e) {
			logger.error("selectCountTotal() ERROR : " + e.getMessage());
		}
		
		return total;
	}

	// 게시글 조회수 count++;
	public void updateHitOfArticle(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_HIT_OF_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("countHitArticle : " + e.getMessage());
		}
	}
	
	// 댓글 삭제 
	public int deleteComment(String no) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 현재 댓글 count. (댓글이 없습니다. 문자열 출력 여부 확인을 위함)
	public int currentCommentsCount(String no) {

		int currentComment = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.CURRENT_COMMENTS_COUNT);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				currentComment = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.error("currentCommentsCount error : " + e.getMessage());
		}
		return currentComment;
	}

	// 댓글 등록 시 댓글 count++;
	/*
	public void updateArticleForComment(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_PLUS_ARTICLE_FOR_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error("updateArticleForComment() ERROR : " + e.getMessage());
		}
	}
	*/

	// 댓글 삭제 시 댓글 count--;
	/*
	public void deleteArticleForComment(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_MINUS_ARTICLE_FOR_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error("deleteArticleForComment error : " + e.getMessage());
		}
	}
	*/
	
	// 현재 댓글 update
	public void updateCommentCount(String no) {
		logger.info("updateCommentCount()...1");
		int result = currentCommentsCount(no);
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.CURRENT_COMMENTS_UPDATE);
			psmt.setInt(1, result);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
			logger.info("updateCommentCount()...2");
			
		} catch (Exception e) {
			logger.error("updateCommentCount ERROR : " + e.getMessage());
		}
		logger.info("updateCommentCount()...3 END");
	}
}
