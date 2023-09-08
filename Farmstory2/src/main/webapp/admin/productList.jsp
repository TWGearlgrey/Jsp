<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>

	/*
	const success = ${success};
	
	if(success == 200){
		alert('주문이 삭제되었습니다.');
	}
	*/

	$(function() {
		
		$('input[name=all]').change(function() {
			
			const isChecked = $(this).is(':checked');
			
			if(isChecked) {
				// 전체 선택
				$('input[name=chk_prod]').prop('checked', true);
				
			}else {
				// 전체 선택 해제
				$('input[name=chk_prod]').prop('checked', false);
			}
		});
		
		$('.productDelete').click(function(e) {
			e.preventDefault();
			
			// 선택된 항목 체크
			const query = "input[name='chk_prod']:checked";
			const selectedElements = document.querySelectorAll(query);
			const count = selectedElements.length;
			console.log("counst : " + count);
			
			if(count == 0){
				alert('선택된 항목이 없습니다.');
				return false;
				
			}else if(confirm('총 '+count+'개의 상품을 선택하셨습니다.\n선택한 상품을 삭제하시겠습니까?')){
				$('#formCheck_prod').submit();
			}
		});
	});
	
</script>
        <main>
            <aside>
			    <h3>주요기능</h3>
			    <ul>
			        <li class="on"><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
			        <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
			        <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
			    </ul>
			</aside>
            <section id="productList">
                <nav>
                    <h3>상품목록</h3>
                </nav>

                <article>
                	<form id="formCheck_prod" action="/Farmstory2/admin/productList.do" method="post">
	                    <table border="0">
	                        <tr>
	                            <th><input type="checkbox" name="all"/></th>
	                            <th>사진</th>
	                            <th>상품번호</th>
	                            <th>상품명</th>
	                            <th>구분</th>
	                            <th>가격</th>
	                            <th>재고</th>
	                            <th>등록일</th>
	                        </tr>
	                        <c:forEach var="prod" items="${products}">
	                        <tr>
	                        	<td style="display: none;">
	                        		<input type="hidden" name="thumb1" value="${prod.thumb1}">
		                        	<input type="hidden" name="thumb2" value="${prod.thumb2}">
		                        	<input type="hidden" name="thumb3" value="${prod.thumb3}">
	                        	</td>
	                            <td class="chk_prod"><input type="checkbox" name="chk_prod" value="${prod.pNo}"/></td>
	                            <td><img src="/Farmstory2/thumb/${prod.thumb1}" class="thumb" alt="샘플1"></td>
	                            <td>${prod.pNoWithComma}</td>
	                            <td>${prod.pName}</td>
	                            <td>
									<c:if test="${prod.type == 1}">과일</c:if>
									<c:if test="${prod.type == 2}">야채</c:if>
									<c:if test="${prod.type == 3}">곡물</c:if>
								</td>
	                            <td>${prod.priceWithComma}</td>
	                            <td>${prod.stockWithComma}</td>
	                            <td>${prod.rdate}</td>
	                        </tr>
	                        </c:forEach>
	                    </table>
	                    <c:if test="${products.isEmpty()}">
	                    	<h3 style="text-align: center; margin: 40px;">등록된 상품이 없습니다.</h3>
	                    </c:if>
					</form>
                    <p>
                    	<c:if test="${!products.isEmpty()}">
                        <a href="#" class="productDelete">선택삭제</a>
                        </c:if>
                        <a href="/Farmstory2/admin/productRegister.do" class="productRegister">상품등록</a>
                    </p>
                    
                    <c:if test="${!products.isEmpty()}">
                    <p class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                        <a href="./productList.do?pg=${pageGroupStart - 1}" class="prev"><</a>
                        </c:if>
                        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        <a href="./productList.do?pg=${i}" class="${(currentPage == i)? 'on':''}">[${i}]</a>
                        </c:forEach>
                        <c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="./productList.do?pg=${pageGroupStart + 1}" class="next"">></a>
                        </c:if>
                    </p>
                    </c:if>

                </article>

                
            </section>
        </main>
<%@ include file="./_footer.jsp" %>