package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertOrder(OrderDTO dto) {
		logger.info("insertOrder()... START");
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ORDER);
			psmt.setInt(1, dto.getOrderProduct());
			psmt.setInt(2, dto.getOrderCount());
			psmt.setInt(3, dto.getOrderDelivery());
			psmt.setInt(4, dto.getOrderPrice());
			psmt.setInt(5, dto.getOrderTotal());
			psmt.setString(6, dto.getReceiver());
			psmt.setString(7, dto.getHp());
			psmt.setString(8, dto.getZip());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
			psmt.setString(11, dto.getOrderEtc());
			psmt.setString(12, dto.getOrderUser());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_ORDER_NO);
			logger.debug("rs : " + rs);
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
			logger.info("insertOrder()... END");
			
		} catch (Exception e) {
			logger.error("insertOrder ERROR : " + e.getMessage());
		}
		return no;
	}
	
	public OrderDTO selectOrder(String orderNo) {
		return null;
	}
	
	public List<OrderDTO> selectOrders() {
		return null;
	}
	
	public void updateOrder(OrderDTO dto) {
		
	}
	
	public void deleteOrder(String orderNo) {
		
	}
	
}
