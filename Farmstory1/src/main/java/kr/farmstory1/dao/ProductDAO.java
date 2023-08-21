package kr.farmstory1.dao;

import java.util.List;

import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;
import kr.farmstory1.dto.ProductDTO;

public class ProductDAO extends DBHelper {
	
	// 기본 CRUD
	public void insertProduct(ProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getType());
			psmt.setString(2, dto.getpName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6, dto.getThumb1());
			psmt.setString(7, dto.getThumb2());
			psmt.setString(8, dto.getThumb3());
			psmt.setString(9, dto.getSeller());
			psmt.setString(10, dto.getEtc());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProductDTO selectProduct(int pNo) {
		return null;
	}
	
	public List<ProductDTO> selectProducts() {
		return null;
	}
	
	public void updateProduct(ProductDTO dto) {}
	
	public void deleteProduct(int pNo) {}
	
}