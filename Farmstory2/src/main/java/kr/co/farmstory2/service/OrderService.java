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
	
	public List<OrderDTO> selectOrders(int start) {
		return dao.selectOrders(start);
	}
	
	public void updateOrder(OrderDTO dto) {
		dao.updateOrder(dto);
	}
	
	public void deleteOrder(String orderNo) {
		dao.deleteOrder(orderNo);
	}
	
	// 리스트 페이징 구현 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public int selectCountOrdersTotal() {
		return dao.selectCountOrdersTotal();
	}
	
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
