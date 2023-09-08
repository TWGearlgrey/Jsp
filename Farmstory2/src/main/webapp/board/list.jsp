<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<!--
	- group 값을 참조하기 위해 include 태그를 사용
	- include 지시자는 표현언어 참조 안됨
-->
<jsp:include page="./_aside${group}.jsp"/>
			<section class="asideList">
			    <h3>글목록</h3>
			    
			    <form action="${ctxPath}/board/list.do" method="get" class="search">
			    	<input type="hidden" name="group" value="${group}">
			    	<input type="hidden" name="cate" value="${cate}">
		            <input type="text" name="search" placeholder="제목 검색" required>
		            <input type="submit" value="검색">
		        </form>
		        
			    <article>
			        <table border="0">
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <c:forEach var="article" items="${articles}">
			            <tr>
			                <td>${pageStartNum = pageStartNum-1}</td>
			                <td>
			                	<a href="./view.do?group=${group}&cate=${cate}&no=${article.no}">${article.title}</a>
			                	&nbsp;[${article.comment}]&nbsp;
			                	<c:if test="${article.file > 0}">
			                	<img src="../images/floppy_disc_icon.png" alt="fileIcon">
			                	</c:if>
		                	</td>
			                <td>${article.nick}</td>
			                <td>${article.rdate}</td>
			                <td>${article.hit}</td>
			            </tr>
			            </c:forEach>
			        </table>
			    </article>
			
			    <!-- 페이지 네비게이션 -->
		        <div class="paging">
		        	<c:if test="${pageGroupStart > 1}">
		            	<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}" class="prev">이전</a>
		            </c:if>
		            
		            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            	<c:choose>
		            		<c:when test="${search == null}">
		            			<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${i}" class="num ${currentPage == i?'current':'off'}">${i}</a>
		            		</c:when>
		            		<c:otherwise>
		            			<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&search=${search}&pg=${i}" class="num ${currentPage == i?'current':'off'}">${i}</a>
		            		</c:otherwise>
		            	</c:choose>
		            </c:forEach>
		            
		            <c:if test="${pageGroupEnd < lastPageNum}">
		            	<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}" class="next">다음</a>
		            </c:if>
		        </div>
			
			    <!-- 글쓰기 버튼 -->
			    <a href="./write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
			</section>
			<!-- 내용 끝 -->

        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>