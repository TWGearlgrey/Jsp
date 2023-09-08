<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
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
                            <td><input type="checkbox" name=""/></td>
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

                    <p>
                        <a href="#" class="productDelete">선택삭제</a>
                        <a href="/Farmstory2/admin/productRegister.do" class="productRegister">상품등록</a>
                    </p>
                    
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

                </article>

                
            </section>
        </main>
<%@ include file="./_footer.jsp" %>