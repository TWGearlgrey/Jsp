<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<Script>

	window.onload = function() {
		
		const userList = document.getElementsByClassName('showPopup')[0];
		
		userList.addEventListener('click', function(e) {
			e.preventDefault();
			
			/* 상세확인 클릭시 uid를 ajax로 날려서 데이터 받아오기 */
			
		});
	}

</Script>
        <main>
            <%@ include file="./_aside.jsp" %>
            <section id="orderList">
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
                            <td>${user.uid}</td>
                            <td>${user.name}</td>                            
                            <td>${user.nick}</td>
                            <td>${user.email}</td>
                            <td>${user.hp}</td>
                            <td>
                                <select name="grade">
                                    <option ${(user.role eq 'USER')?'selected':''}>USER</option>
                                    <option ${(user.role eq 'VIP')?'selected':''}>VIP</option>
                                    <option ${(user.role eq 'VVIP')?'selected':''}>VVIP</option>
                                    <option ${(user.role eq 'SELLER')?'selected':''}>SELLER</option>
                                    <option ${(user.role eq 'ADMIN')?'selected':''}>ADMIN</option>
                                </select>
                            </td>
                            <td>2023-01-01 13:06:14</td>
                            <td>
                            	<a data-uid="${user.uid}" href="#" class="showPopup">[상세확인]</a>
                           	</td>
                        </tr>
                        </c:forEach>
                    </table>

                    <p>
                        <a href="#" class="orderDelete">선택수정</a>                        
                    </p>
                    
                    <p class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                        <a href="./productList.do?pg=${pageGroupStart - 1}" class="prev"><</a>
                        </c:if>
                        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        <a href="./productList.do?pg=${i}" class="${(currentPage == i)? 'on':''}">[${i}]</a>
                        </c:forEach>
                        <c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="//productList.do?pg=${pageGroupStart + 1}" class="next"">></a>
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
                            <td class="user_uid"></td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td class="user_name"></td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td class="user_nick"></td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td class="user_email"></td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td class="user_hp"></td>
                        </tr>
                        <tr>
                            <td>등급</td>
                            <td class="user_role"></td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <p class="user_addr1">
                                    
                                </p>
                                <p class="user_addr2">
                                    
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>아이피</td>
                            <td class="user_regip"></td>
                        </tr>
                        <tr>
                            <td>회원가입일</td>
                            <td class="user_regDate"></td>
                        </tr>
                    </table>
                </article>
            </section>
        </div>
<%@ include file="./_footer.jsp" %>