<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>

	let   count    = 1;
	const price    = ${product.price};
	const delivery = ${product.delivery};
	const pNo      = ${product.pNo};
	
	console.log('count    : ' + count);
	console.log('price    : ' + price);
	console.log('delivery : ' + delivery);
	console.log('pNo      : ' + pNo);
	
	function formatNumberWithCommas(number) {
	    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	function counting() {
		count = $('input[name=count]').val();
		console.log('count : ' + count);
		
		// 주문 상품 확인 변경
		let totalPrice = price * count;
		let totalPriceWithDeliveryPrice = (price*count) + delivery;
		
		// form input값 변경
		$('input[name=count]').val(count);
		$('input[name=total]').val(totalPrice);
		if(totalPrice > 30000) {
			$('input[name=final]').val(totalPrice);
		}else {
			$('input[name=final]').val(totalPriceWithDeliveryPrice);
		}
		
		console.log('totalPrice : ' + totalPrice);
		console.log('totalPriceWithDeliveryPrice : ' + totalPriceWithDeliveryPrice);
		
		document.getElementsByClassName('total')[0].innerHTML = formatNumberWithCommas(totalPrice)+'원';
	}
	
	window.onload = function() {
		const formOrder = document.getElementById('formOrder');
		const btnOrder = document.getElementsByClassName('btnOrder')[0];
		btnOrder.onclick = (e)=>{
			e.preventDefault();
			if(confirm('구매수량 ' + count + '개, 합계 ' + formatNumberWithCommas($('input[name=final]').val()) + '원입니다.\n구매페이지로 이동하시겠습니까?')) {
				formOrder.submit();
			}
		};
	};
	
</script>
		<div id="sub">
		    <div><img src="../images/sub_top_tit2.png" alt="MARKET"></div>
		    <section class="market">
		        <aside>
		            <img src="../images/sub_aside_cate2_tit.png" alt="장보기"/>
		
		            <ul class="lnb">
		                <li class="on"><a href="./list.do">장보기</a></li>
		            </ul>
		        </aside>
		        <article class="view">
		            <nav>
		                <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
		                <p>
		                    HOME > 장보기 > <em>장보기</em>
		                </p>
		            </nav>
		
		            <!-- 내용 시작 -->
                    <h3>기본정보</h3>
                    <div class="basic">
                        <img src="/Farmstory2/thumb/${product.thumb2}" alt="${product.pName}">

                        <table border="0">                            
                            <tr>
                                <td>상품명</td>
                                <td>${product.pName}</td>
                            </tr>
                            <tr>
                                <td>상품코드</td>
                                <td>${product.pNo}</td>
                            </tr>
                            <tr>
                                <td>배송비</td>
                                <td>
                                	<c:choose>
                                		<c:when test="${product.delivery ne 0}">
                                			<span>${product.deliveryWithComma}</span>원
		                                    <em>3만원 이상 무료배송</em>
                                		</c:when>
                                		<c:otherwise>
                                			<span>무료</span>
                                		</c:otherwise>
                                	</c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td>판매가격</td>
                                <td>${product.priceWithComma}원</td>
                            </tr>
                            <tr>
                                <td>구매수량</td>
                                <td>
                                    <input type="number" name="count" min="1" value="1" oninput="counting()">
                                </td>
                            </tr>
                            <tr>
                                <td>합계</td>
                                <td class="total">${product.priceWithComma}원</td> 
                                <!-- 스크립트 처리하여 곱셈 연산 -->
                            </tr>
                        </table>
                        <form id="formOrder" action="${ctxPath}/market/order.do" method="post">
		                	<input type="hidden" name="thumb2"   value="${product.thumb2}">
		                	<input type="hidden" name="pName"    value="${product.pName}">
		                	<input type="hidden" name="pNo"      value="${product.pNo}">
		                	<input type="hidden" name="delivery" value="${product.delivery}">
		                	<input type="hidden" name="price"    value="${product.price}">
		                	<input type="hidden" name="count" value="1">
		                	<input type="hidden" name="total" value="${product.price}">
		                	<input type="hidden" name="final" value="${product.price + product.delivery}">
	                	</form>
						<a href="#" class="btnOrder">
							<img src="../images/market_btn_order.gif" alt="바로 구매하기"/>
						</a>              
                    </div>
                    <h3>상품설명</h3>
                    <div class="detail">
                        <img src="/Farmstory2/thumb/${product.thumb3}" alt="${product.etc}">
                    </div>

                    <h3>배송정보</h3>
                    <div class="delivery">
                        <p>
                            입금하신 이후 택배송장번호는 SMS(문자서비스)를 통해 고객님께 안내해드립니다.
                        </p>
                    </div>

                    <h3>교환/반품</h3>                  
                    <div class="exchange">
                        <table border="0">
                            <tr>
                                <td>교환 반품이 가능한 경우</td>
                                <td>
                                    <ul>
                                        <li>팜스토리 상품에 하자가 있거나 불량인 경우</li>
                                        <li>채소, 과일, 양곡등의 식품은 만1일 이내</li>
                                        <li>기타 상품은 수령일로부터 영업일 기준 일주일 이내</li>
                                        <li>받으신 상품이 표시사항과 다른 경우에는 받으신 날로부터 일주일 이내</li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td>교환 반품이 불가능한 경우</td>
                                <td>
                                    <ul>
                                        <li>신선 식품의 경우 단순히 마음에 들지 않는 경우</li>
                                        <li>단순 변심으로 상품이 가치가 훼손돼서 판매가 어려운 경우</li>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <!-- 내용 끝 -->
                </article>
            </section>
        </div>
<%@ include file="../_footer.jsp" %>