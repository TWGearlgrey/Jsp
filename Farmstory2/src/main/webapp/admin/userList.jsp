<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<Script>

</Script>
        <main>
            <aside>
			    <h3>주요기능</h3>
			    <ul>
			        <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
			        <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
			        <li class="on"><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
			    </ul>
			</aside>
            <section id="userList">
                <nav>
                    <h3>회원목록</h3>
                </nav>

                <article>

                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>별명</th>
                            <th>이메일</th>
                            <th>휴대폰</th>
                            <th>등급</th>
                            <th>가입일</th>
                            <th>확인</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                        <tr>
                            <td><input type="checkbox" name=""/></td>
                            <td class="u_uid">${user.uid}</td>
                            <td class="u_name">${user.name}</td>                            
                            <td class="u_nick">${user.nick}</td>
                            <td class="u_email">${user.email}</td>
                            <td class="u_hp">${user.hp}</td>
                            <td>
                                <select name="grade">
                                    <option ${(user.role eq 'USER')?'selected':''}>USER</option>
                                    <option ${(user.role eq 'VIP')?'selected':''}>VIP</option>
                                    <option ${(user.role eq 'VVIP')?'selected':''}>VVIP</option>
                                    <option ${(user.role eq 'SELLER')?'selected':''}>SELLER</option>
                                    <option ${(user.role eq 'ADMIN')?'selected':''}>ADMIN</option>
                                </select>
                            </td>
                            <td class="u_regDate">${user.regDate}</td>
                            <td class="u_role" style="display: none">${user.role}</td>
                            <td class="u_regip" style="display: none">${user.regip}</td>
                            <td class="u_addr1" style="display: none">${user.addr1}</td>
                            <td class="u_addr2" style="display: none">${user.addr2}</td>
                            <td>
                            	<a href="#" class="showUserPopup">[상세확인]</a>
                           	</td>
                        </tr>
                        </c:forEach>
                    </table>

                    <p>
                        <a href="#" class="orderDelete">선택수정</a>                        
                    </p>
                    
                    <p class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                        <a href="./userList.do?pg=${pageGroupStart - 1}" class="prev"><</a>
                        </c:if>
                        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        <a href="./userList.do?pg=${i}" class="${(currentPage == i)? 'on':''}">[${i}]</a>
                        </c:forEach>
                        <c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="./userList.do?pg=${pageGroupStart + 1}" class="next"">></a>
                        </c:if>
                    </p>
                </article>
            </section>
        </main>


        <div id="userPopup">
            <section>
                <nav>
                    <h1>사용자 상세정보</h1>
                    <button class="btnClose">닫기</button>
                </nav>

                <article>  
                    <h3>기본정보</h3>
                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td class="u_uid"></td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td class="u_name"></td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td class="u_nick"></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td class="u_email"></td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td class="u_hp"></td>
                        </tr>
                        <tr>
                            <td>등급</td>
                            <td class="u_role"></td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <p class="u_addr1">
                                    
                                </p>
                                <p class="u_addr2">
                                    
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>아이피</td>
                            <td class="u_regip"></td>
                        </tr>
                        <tr>
                            <td>회원가입일</td>
                            <td class="u_regDate"></td>
                        </tr>
                    </table>
                </article>
            </section>
        </div>
<%@ include file="./_footer.jsp" %>