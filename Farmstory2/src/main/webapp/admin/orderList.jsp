<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>

	const success = ${success};
	
	if(success == 200){
		alert('주문이 삭제되었습니다.');
	}

	$(function() {
		
		$('input[name=all]').change(function() {
			
			const isChecked = $(this).is(':checked');
			
			if(isChecked) {
				// 전체 선택
				$('input[name=chk_order]').prop('checked', true);
				
			}else {
				// 전체 선택 해제
				$('input[name=chk_order]').prop('checked', false);
			}
		});
		
		$('.orderDelete').click(function(e) {
			e.preventDefault();
			
			// 선택된 항목 체크
			const query = "input[name='chk_order']:checked";
			const selectedElements = document.querySelectorAll(query);
			const count = selectedElements.length;
			
			if(count == 0){
				alert('선택된 항목이 없습니다.');
				return false;
				
			}else if(confirm('총 '+count+'개의 주문을 선택하셨습니다.\n선택한 주문을 삭제하시겠습니까?')){
				$('#formCheck_order').submit();
			}
		});
	});
	
</script>
        <main>
            <aside>
			    <h3>주요기능</h3>
			    <ul>
			        <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
			        <li class="on"><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
			        <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
			    </ul>
			</aside>
            <section id="orderList">
                <nav>
                    <h3>주문목록</h3>
                </nav>

                <article>
					<form id="formCheck_order" action="/Farmstory2/admin/orderList.do" method="post">
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
	                        <c:forEach var="order" items="${orders}">
	                        <tr>
	                            <td class="chk_order"><input type="checkbox" name="chk_order" value="${order.orderNo}"/></td>
	                            <td class="orderProduct">${order.orderNo}</td>
	                            <td class="pName">${order.pName}</td>                            
	                            <td class="price">${order.orderPriceWithComma}</td>
	                            <td class="count">${order.orderCountWithComma}</td>
	                            <td class="delivery">${order.orderDeliveryWithComma}</td>
	                            <td class="total">${order.orderTotalWithComma}</td>
	                            <td class="orderer">${order.orderUser}</td>
	                            <td class="date">${order.orderDate}</td>
	                            <td><a href="#" class="showPopup">[상세확인]</a></td>
	                            <td class="pNo" style="display: none">${order.orderProduct}</td>
	                            <td class="thumb1" style="display: none">${order.thumb1}</td>
	                            <td class="receiver" style="display: none">${order.receiver}</td>
	                            <td class="address" style="display: none">${order.addr1} ${order.addr2}</td>
	                        </tr>
	                        </c:forEach>
	                    </table>
	                    <c:if test="${orders.isEmpty()}">
	                    	<h3 style="text-align: center; margin: 40px;">주문 내역이 없습니다.</h3>
	                    </c:if>
					</form>
					
					<c:if test="${!orders.isEmpty()}">
                    <p>
                        <a href="#" class="orderDelete">선택삭제</a>                        
                    </p>
                    
                    <p class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                        <a href="./orderList.do?pg=${pageGroupStart - 1}" class="prev"><</a>
                        </c:if>
                        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        <a href="./orderList.do?pg=${i}" class="${(currentPage == i)? 'on':''}">[${i}]</a>
                        </c:forEach>
                        <c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="./orderList.do?pg=${pageGroupStart + 1}" class="next"">></a>
                        </c:if>
                    </p>
                    </c:if>
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
                            <td rowspan="7" class="thumb"><img src="#" alt="사과 500g"></td>
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