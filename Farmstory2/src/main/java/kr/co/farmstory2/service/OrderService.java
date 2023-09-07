package kr.co.farmstory2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.OrderDAO;
import kr.co.farmstory2.dto.OrderDTO;

public enum OrderService {

	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderDAO dao = new OrderDAO();
	
	public int insertOrder(OrderDTO dto) {
		return dao.insertOrder(dto);
	}
	
	public OrderDTO selectOrder(String orderNo) {
		return dao.selectOrder(orderNo);
	}
	
	public List<OrderDTO> selectOrders() {
		return dao.selectOrders();
	}
	
	public void updateOrder(OrderDTO dto) {
		dao.updateOrder(dto);
	}
	
	public void deleteOrder(String orderNo) {
		dao.deleteOrder(orderNo);
	}
}
