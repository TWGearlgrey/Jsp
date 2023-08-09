<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no"); // no이 int형이더라도 문자열로 조회 할 수 있다.
	
	ArticleDAO dao = new ArticleDAO();
	
	// 원글 조회
	ArticleDTO dto = dao.selectArticle(no);
	
	// 댓글 조회
	List<ArticleDTO> comments = dao.selectComments(no);
%>
<script>
	$(function(){
		$('.del').click(function(){
			
			const result = confirm('정말 삭제 하시겠습니까?');
			
			if(result == true){
				return true;
			}else{
				return false;
			}
		});
		
		$('.rpt').click(function(){
			
			const result = confirm('정말 신고 하시겠습니까?');
			
			if(result == true){
				alert('신고되었습니다!');
				return false;
			}else{
				return false;
			}
		});
	});
</script>
<main>
    <section class="view">
        <h3>글보기</h3>
        <table>
            <tr>
                <td>제목</td>
                <td><input type="text" name="title" value="<%= dto.getTitle() %>" readonly/></td>
            </tr>
            <% if(dto.getFile() > 0) { %>
            <tr>
                <td>첨부파일</td>
                <td>
                    <a href="#">2020년 상반기 매출자료.xls</a>
                    <span>0회 다운로드</span>
                </td>
            </tr>
            <% } %>
            <tr>
                <td>내용</td>
                <td>
                    <textarea name="content" readonly><%= dto.getContent() %></textarea>
                </td>
            </tr>
        </table>
        <div>
            <a href="#" class="btnDelete">삭제</a>
            <a href="#" class="btnModify">수정</a>
            <a href="/Jboard1/list.jsp" class="btnList">목록</a>
        </div>  
        
        <!-- 댓글리스트 -->
        <section class="commentList">
            <h3>댓글목록</h3>
            <% for(ArticleDTO comment : comments){ %>
            <article class="comment">
                <span>
                    <span><%= comment.getNick() %></span>
                    <span><%= comment.getRdate() %></span>
                </span>
                <textarea name="comment" readonly><%= comment.getContent() %></textarea>
                
                <% if(sessUser.getUid().equals(comment.getWriter())){ %>
                <div>
                    <a href="/Jboard1/proc/commentDelete.jsp?no=<%= comment.getNo() %>&parent=<%= comment.getParent() %>" class="del">삭제</a>
                    <a href="#" class="mod">수정</a>
                </div>
                <% }else { %>
                <div>
                    <a href="#" class="rpt">신고</a>
                </div>
                <% } %>
            </article>
            <% } %>
            
            <% if(comments.isEmpty()){ %>
            <p class="empty">
                등록된 댓글이 없습니다.
            </p>
            <% } %>
        </section>

        <!-- 댓글입력폼 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form action="/Jboard1/proc/commentProc.jsp" method="post">
            	<input type="hidden" name="parent" readonly value="<%= no %>"/>
            	<input type="hidden" name="writer" readonly value="<%= sessUser.getUid() %>"/>
                <textarea name="content"></textarea>
                <div>
                    <a href="#" class="btnCancel">취소</a>
                    <input type="submit" class="btnWrite" value="작성완료"/>
                </div>
            </form>
        </section>

    </section>
</main>
<%@ include file = "./_footer.jsp" %>