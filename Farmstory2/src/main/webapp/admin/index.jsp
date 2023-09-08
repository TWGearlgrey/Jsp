<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
        <main>
            <aside>
			    <h3>주요기능</h3>
			    <ul>
			        <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
			        <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
			        <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
			    </ul>
			</aside>
            <section>
                <nav>
                    <h3>관리자 메인</h3>
                </nav>

                <article>
                    <h3>
                        <a href="/Farmstory2/admin/productList.do">상품현황</a>
                        <a href="/Farmstory2/admin/productList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>상품번호</th>
                            <th>상품명</th>
                            <th>구분</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>등록일</th>
                        </tr>
                        <c:forEach var="prod" items="${products}" begin="0" step="1" end="2">
                        <tr>
                            <td>${prod.pNo}</td>
                            <td>${prod.pName}</td>
                            <td>${prod.type}</td>
                            <td>${prod.price}</td>
                            <td>${prod.stock}</td>
                            <td>${prod.rdate}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </article>

                <article>
                    <h3>
                        <a href="/Farmstory2/admin/orderList.do">주문현황</a>
                        <a href="/Farmstory2/admin/orderList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>주문번호</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>수량</th>
                            <th>배송비</th>
                            <th>합계</th>
                            <th>주문자</th>
                            <th>주문일</th>
                        </tr>
                        <c:forEach var="order" items="${orders}" begin="0" step="1" end="2">
                        <tr>
                            <td>${order.orderNo}</td>
                            <td>${order.pName}</td>
                            <td>${order.orderPrice}</td>
                            <td>${order.orderCount}</td>
                            <td>${order.orderDelivery}</td>
                            <td>${order.orderTotal}</td>
                            <td>${order.orderUser}</td>
                            <td>${order.orderDate}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </article>
                <article>
                    <h3>
                        <a href="/Farmstory2/admin/userList.do">회원현황</a>
                        <a href="/Farmstory2/admin/userList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>회원아이디</th>
                            <th>이름</th>
                            <th>닉네임</th>
                            <th>휴대폰</th>
                            <th>이메일</th>
                            <th>등급</th>
                            <th>회원가입일</th>
                        </tr>
                        <c:forEach var="user" items="${users}" begin="0" step="1" end="2">
                        <tr>
                            <td>${user.uid}</td>
                            <td>${user.name}</td>
                            <td>${user.nick}</td>
                            <td>${user.hp}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>${user.regDate}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </article>
            </section>
        </main>
<%@ include file="./_footer.jsp" %>