<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<!--
	- group 값을 참조하기 위해 include 태그를 사용
	- include 지시자는 표현언어 참조 안됨
-->
<jsp:include page="./_aside${group}.jsp"/>
			<section class="asideList">
			    <h3>글목록</h3>
			    <article>
			        <table border="0">
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <tr>
			                <td>1</td>
			                <td><a href="./view.do?group=${group}&cate=${cate}">제목입니다.</a>&nbsp;[3]</td>
			                <td>길동이</td>
			                <td>23-09-04</td>
			                <td>12</td>
			            </tr>
			        </table>
			    </article>
			
			    <!-- 페이지 네비게이션 -->
		        <div class="paging">
		            <a href="#" class="prev">이전</a>
		            <a href="#" class="num current">1</a>
		            <a href="#" class="next">다음</a>
		        </div>
			
			    <!-- 글쓰기 버튼 -->
			    <a href="./write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
			</section>
			<!-- 내용 끝 -->

        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>