<%@page import="kr.farmstory1.db.Utils"%>
<%@page import="kr.farmstory1.dto.OrderDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	OrderDAO dao = new OrderDAO();
	
	// 페이지 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산
	if(pg != null){
		currentPage = Integer.parseInt(pg);
	}
	
	// Limit 시작값 계산
	start = (currentPage - 1) * 10;
	
	// 전체 상품 갯수
	total = dao.selectCountOrdersTotal();
	
	// 페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	List<OrderDTO> orders = dao.selectOrders(start);
%>
<script>
	
	$(function() {
		
		$('input[name=all]').change(function() {
			
			const isChecked = $(this).is(':checked');
			
			if(isChecked) {
				// 전체 선택
				$('input[name=chk]').prop('checked', true);
				
			}else {
				// 전체 선택 해제
				$('input[name=chk]').prop('checked', false);
			}
		});
		
		$('.orderDelete').click(function(e) {
			e.preventDefault();
			
			$('#formCheck').submit();
		});
	});
</script>
<main>
    <%@ include file="./_aside.jsp" %>
    <section id="orderList">
        <nav>
            <h3>주문목록</h3>
        </nav>

        <article>
			<form id="formCheck" action="./proc/deleteOrders.jsp" method="get">
	            <table border="0">
	                <tr>
	                    <th><input type="checkbox" name="all"/></th>
	                    <th>주문번호</th>
	                    <th>상품명</th>
	                    <th>판매가격</th>
	                    <th>수량</th>
	                    <th>배송비</th>
	                    <th>합계</th>
	                    <th>주문자</th>
	                    <th>주문일</th>
	                    <th>확인</th>
	                </tr>
	                <% for(OrderDTO order : orders){ %>
	                
	                <tr>
	                    <td class="chk"><input type="checkbox" name="chk" value="<%= order.getOrderNo() %>"/></td>
	                    <td class="orderProduct"><%= order.getOrderNo() %></td>
	                    <td class="pName"><%= order.getpName() %></td>                            
	                    <td class="price"><%= Utils.comma(order.getOrderPrice()) %>원</td>
	                    <td class="count"><%= order.getOrderCount() %></td>
	                    <td class="delivery"><%= Utils.comma(order.getOrderDelivery()) %>원</td>
	                    <td class="total"><%= Utils.comma(order.getOrderTotal()) %>원</td>
	                    <td class="orderer"><%= order.getOrderUser() %></td>
	                    <td class="date"><%= order.getOrderFullDate() %></td>
	                    <td><a href="#" class="showPopup">[상세확인]</a></td>
	                    <td class="hidden pNo"><%= order.getOrderProduct() %></td>
	                    <td class="hidden thumb1"><%= order.getThumb1() %></td>
	                    <td class="hidden receiver"><%= order.getReceiver() %></td>
	                    <td class="hidden address"><%= order.getAddr1() + " " + order.getAddr2() %></td>
	                </tr>
	                <% } %>
	            </table>
			</form>
            <p>
                <a href="#" class="orderDelete">선택삭제</a>
            </p>
            
            <p class="paging">
                <% if(pageGroupStart > 1){ %>
	            <a href="./orderList.jsp?pg=<%= pageGroupStart - 1 %>" class="prev"><</a>
	            <% } %>
	            
	            <% for(int i=pageGroupStart ; i<=pageGroupEnd ; i++){ %>
	            <a href="./orderList.jsp?pg=<%= i %>" class="<%= (currentPage == i)?"on":"" %>">[<%= i %>]</a>
	            <% } %>
	            
	            <% if(pageGroupEnd < lastPageNum){ %>
	            <a href="./orderList.jsp?pg=<%= pageGroupEnd + 1 %>" class="next">></a>
	            <% } %>
            </p>
        </article>
    </section>
</main>

<div id="orderPopup">
    <section>
        <nav>
            <h1>상세주문내역</h1>
            <button class="btnClose">닫기</button>
        </nav>

        <article>  
            <h3>기본정보</h3>
            <table border="0">
                <tr>
                    <td rowspan="7" class="thumb"><img src="./images//sample_item1.jpg" alt="사과 500g"></td>
                    <td>상품번호</td>
                    <td class="orderProduct">1011</td>
                </tr>
                <tr>
                    <td>상품명</td>
                    <td class="pName">사과 500g</td>
                </tr>
                <tr>
                    <td>판매가격</td>
                    <td class="price">4,000원</td>
                </tr>
                <tr>
                    <td>수량</td>
                    <td class="count">2개</td>
                </tr>
                <tr>
                    <td>배송비</td>
                    <td class="delivery">3,000원</td>
                </tr>
                <tr>
                    <td>합계</td>
                    <td class="total">11,000원</td>
                </tr>
                <tr>
                    <td>주문자</td>
                    <td class="orderer">홍길동</td>
                </tr>                        
            </table>

            <h3>배송지 정보</h3>
            <table border="0">
                <tr>
                    <td>받는분</td>
                    <td class="receiver">홍길동</td>
                </tr>
                <tr>
                    <td>배송지</td>
                    <td class="address">부산광역시 부산진구 대연동 120 루미너스 10층</td>
                </tr>
            </table>
        </article>
    </section>
</div>
<%@ include file="./_footer.jsp" %>