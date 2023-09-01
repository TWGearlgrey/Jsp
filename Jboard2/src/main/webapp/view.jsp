<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "./_header.jsp" %>
<script>
	
	$(function() {
		
		// 게시글 삭제 컨펌
		$(document).on('click', '.btnRemove', function(e) {
			
			if(confirm('정말 삭제하시겠습니까?')){
				return true;
			}else {
				return false;
			}
		});
		
		
		// 댓글 삭제(동적 생성 이벤트 구현)
		$(document).on('click', '.remove', function(e) {
			
			if(confirm('정말 삭제하시겠습니까?')){
				e.preventDefault();
				//alert('클릭!');
				const no = $(this).data('no');
				const parent = $(this).data('pno');
				const article = $(this).parent().parent();
				console.log('no : ' + no);
				console.log('article : ' + article);
				
				const jsonData = {
					"kind": "REMOVE", 
					"no": no, 
					"parent": parent
				}
				
				$.ajax({
					url: '/Jboard2/comment.do',
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
					
						if(data.result > 0) {
							
							alert('댓글이 삭제되었습니다.');
							
							// 화면처리
							article.remove();
							
							if(data.currentComment < 1) {
								$('.empty').show();
							}
							
							console.log('currentComment : ' + data.currentComment);
							
						}
					}
				});
				
			}else {
				return false;
			}
		});
		
		
		// 댓글 입력
		$('#btnComment').click(function(e) {
			e.preventDefault();
			
			const parent  = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer  = $('#formComment > input[name=writer]').val();
			
			const jsonData = {
				"parent": parent,	
				"content": content,
				"writer": writer
			};
			
			console.log('jsonData : ' + jsonData);
			
			$.ajax({
				url: '/Jboard2/comment.do', 
				type: 'post', 
				data: jsonData, 
				dataType: 'json',
				success: function(data) {
					
					console.log(data);
					console.log(content);
					
					if(data.result > 0) {
						alert('댓글이 등록됐습니다.');
						
						// 동적 화면 생성
						const dt    = new Date();
						const year  = dt.getFullYear().toString().substr(2, 4);
						const month = dt.getMonth()+1;
						const date  = dt.getDate();
						const now   = year + "-" + month + "-" + date;
						console.log(year);
						console.log(month);
						console.log(date);
						console.log(now);
						
						const article = `<article>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>`+now+`</span>
											<p class='content'>`+content+`</p>
											<div>
												<a href='#' class='remove'>삭제</a>
												<a href='#' class='modify'>수정</a>
											</div>
										</article>`;
						
						$('.commentList').append(article);
						$('#formComment > textarea[name=content]').val('');
						$('.empty').hide();
						
					}else {
						alert('댓글 등록이 실패했습니다.\n다시 시도해주세요.');
						
						
					}
				}
			});
		});
	});
	
</script>
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
	                    <a href="#" class="remove" data-no="${comment.no}" data-pno="${article.no}">삭제</a>
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
            <form id="formComment" action="#" method="post">
            	<input type="hidden" name="parent" value="${no}">
            	<input type="hidden" name="writer" value="${sessUser.uid}">
                <textarea name="content"></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>

    </section>
</main>
<%@ include file = "./_footer.jsp" %>