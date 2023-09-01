<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "./_header.jsp" %>
<main id="board">
    <section class="view">
        
        <table border="0">
            <caption>글보기</caption>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${article.title}" readonly/></td>
            </tr>
            <c:if test="${article.file > 0}">
	            <tr>
	                <th>파일</th>
	                <td>
		                <a href="/Jboard2/fileDownload.do?fno=${article.fileDto.fno }" class="downloadZone">${article.fileDto.oriName}</a>&nbsp;
		                <span>${article.fileDto.download}</span>회 다운로드
	                </td>
	            </tr>
            </c:if>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly>${article.content}</textarea>
                </td>
            </tr>                    
        </table>
        
        <div>
        	<c:if test="${article.writer == sessUser.uid}">
	            <a href="/Jboard2/delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
	            <a href="/Jboard2/modify.do?no=${article.no}" class="btn btnModify">수정</a>
            </c:if>
            <a href="/Jboard2/list.do" class="btn btnList">목록</a>
        </div>

        <!-- 댓글목록 -->
        <section class="commentList">
            <h3>댓글목록</h3>                   
			
			<c:forEach var="comment" items="${requestScope.comments}">
	            <article>
	                <span class="nick">${comment.nick}</span>
	                <span class="date">${comment.rdate}</span>
	                <p class="content">${comment.content}</p> 
	                <c:if test="${comment.writer == sessUser.uid}">                       
	                <div>
	                    <a href="#" class="remove">삭제</a>
	                    <a href="#" class="modify">수정</a>
	                </div>
	                </c:if>
	            </article>
            </c:forEach>
            <c:if test="${article.comment < 1}">
            	<p class="empty">등록된 댓글이 없습니다.</p>
            </c:if>

        </section>

        <!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="/Jboard2/view.do?parent=${article.no}" method="post">
            	<input type="hidden" name="writer" value="${sessUser.uid}">
                <textarea name="content">댓글내용 입력</textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>

    </section>
</main>
<%@ include file = "./_footer.jsp" %>