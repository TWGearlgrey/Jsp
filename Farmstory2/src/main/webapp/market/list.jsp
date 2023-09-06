<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
		<div id="sub">
		    <div><img src="../images/sub_top_tit2.png" alt="MARKET"></div>
		    <section class="market">
		        <aside>
		            <img src="../images/sub_aside_cate2_tit.png" alt="장보기"/>
		
		            <ul class="lnb">
		                <li class="on"><a href="./list.do">장보기</a></li>
		            </ul>
		        </aside>
		        <article class="list">
		            <nav>
		                <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
		                <p>
		                    HOME > 장보기 > <em>장보기</em>
		                </p>
		            </nav>
		
		            <!-- 내용 시작 -->
                    <p class="sort">
                        <a href="./list.do?type=0" class="${type == 0?'on':''}">전체(10) |</a>
                        <a href="./list.do?type=1" class="${type == 1?'on':''}">과일 |</a>
                        <a href="./list.do?type=2" class="${type == 2?'on':''}">야채 |</a>
                        <a href="./list.do?type=3" class="${type == 3?'on':''}">곡류</a>
                    </p>
                    <table border="0">
                        <c:forEach var="prod" items="${products}">
                        <tr>
                            <td>
                                <a href="#"><img src="/Farmstory2/thumb/${prod.thumb1}" alt="${prod.pName}"></a>
                            </td>
                            <c:if test="${prod.type eq 1}"><td>과일</td></c:if>
                            <c:if test="${prod.type eq 2}"><td>야채</td></c:if>
                            <c:if test="${prod.type eq 3}"><td>곡류</td></c:if>
                            <td><a href="#">${prod.pName}</a></td>
                            <td><strong>${prod.priceWithComma}</strong>원</td>
                        </tr>
                        </c:forEach>
                    </table>

                    <p class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                        <a href="#"><</a>
                        </c:if>
                        
                        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                        <a href="/Farmstory2/market/list.do?type=${type}&pg=${i}" class="${currentPage == i?'on':''}">[${i}]</a>
                        </c:forEach>
                        
                        <c:if test="${pageGroupEnd < lastPageNum}">
                        <a href="#">></a>
                        </c:if>
                    </p>

                    <!-- 내용 끝 -->

                </article>
            </section>

        </div>
<%@ include file="../_footer.jsp" %>