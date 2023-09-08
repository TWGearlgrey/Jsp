package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertOrder(OrderDTO dto) {
		logger.debug("insertOrder()... START");
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
			logger.debug("insertOrder()... END");
			
		} catch (Exception e) {
			logger.error("insertOrder ERROR : " + e.getMessage());
		}
		return no;
	}
	
	public OrderDTO selectOrder(String orderNo) {
		return null;
	}
	
	public List<OrderDTO> selectOrders(int start) {
		List<OrderDTO> orders = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ORDERS);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setOrderNo(rs.getInt(1));
				dto.setOrderProduct(rs.getInt(2));
				dto.setOrderCount(rs.getInt(3));
				dto.setOrderDelivery(rs.getInt(4));
				dto.setOrderPrice(rs.getInt(5));
				dto.setOrderTotal(rs.getInt(6));
				dto.setReceiver(rs.getString(7));
				dto.setHp(rs.getString(8));
				dto.setZip(rs.getString(9));
				dto.setAddr1(rs.getString(10));
				dto.setAddr2(rs.getString(11));
				dto.setOrderEtc(rs.getString(12));
				dto.setOrderUser(rs.getString(13));
				dto.setOrderDate(rs.getString(14));
				dto.setpName(rs.getString(15));
				dto.setName(rs.getString(16));
				dto.setThumb1(rs.getString(17));
				orders.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectOrders() ERROR : " + e.getMessage());
		}
		return orders;
	}
	
	public void updateOrder(OrderDTO dto) {
		
	}
	
	public void deleteOrder(String orderNo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ORDER);
			psmt.setString(1, orderNo);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("deleteOrder() ERROR : " + e.getMessage());
		}
	}
	
	public int selectCountOrdersTotal() {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_ORDERS_ALL);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
			
		}catch (Exception e) {
			logger.error("selectCountOrdersTotal() ERROR : " + e.getMessage());
		}
		return total;
	}
	
}
