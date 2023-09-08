<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>

	$(function() {
		
		// 게시글 삭제 컨펌
		$('.btnDelete').click(function(e) {
			if(confirm('정말 삭제하시겠습니까?')){
				return true;
			}else {
				return false;
			}
		});
		
		
		
		// 댓글 삭제(동적 이벤트 처리)
		$(document).on('click', '.del', function(e){
			e.preventDefault();
			
			if(confirm('정말 삭제하시겠습니까?')){
				e.preventDefault();
				
				const no = $(this).data('no');
				const parent = $('.view > input[name=articleNo]').val();
				const article = $(this).parent().parent();
				console.log('no      : ' + no);
				console.log('parent  : ' + parent);
				console.log('article : ' + article);
				
				const jsonData = {
					"kind": "REMOVE",
					"no": no,
					"parent": parent
				}
				
				$.ajax({
					url: '/Farmstory2/board/comment.do',
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
						
						if(data.result > 0) {
							
							alert('댓글이 삭제되었습니다.');
							
							// 화면처리 (새로고침 없이 댓글 안보이게 처리)
							article.remove();
							
							if(data.currentComment < 1) {
								// 동적일때만 적용 됨
								//$('.empty').show();
								
								// 정적일때 동적으로 적용
								const empty = `<p class='empty'>등록된 댓글이 없습니다.</p>`
								$('.commentList').append(empty);
							}
							
							console.log('currentComment : ' + data.currentComment);
						}
					}
				});
				
			}else{
				return false;
			}
			
		});
		
		
		// 댓글 입력
		$('#btnComment').click(function(e) {
			e.preventDefault();
			
			const parent  = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer  = $('#formComment > input[name=writer]').val();
			
			console.log("parent  : " + parent);
			console.log("content : " + content);
			console.log("writer  : " + writer);
			
			const jsonData = {
				"parent": parent,	
				"content": content,
				"writer": writer
			};
			
			console.log('jsonData : ' + jsonData);
			
			$.ajax({
				url: '/Farmstory2/board/comment.do', 
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

						//const month = dt.getMonth()+1;
						//const date = dt.getDate();
						let month = dt.getMonth()+1;
						if(dt.getMonth()+1 < 10) {
							month = '0' + month;
						}
						let date = dt.getDate();
						if(dt.getDate() < 10) {
							date = '0' + date;
						}
						
						const now   = year + "-" + month + "-" + date;
						
						console.log(year);
						console.log(month);
						console.log(date);
						console.log(now);
						console.log(data.no);
						
						const article = `<article class='comment'>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>`+now+`</span>
											<textarea name='content' readonly>`+content+`</textarea>
							             
											<div>
												<a href='#' class='del' data-no='`+data.no+`' data-pno='`+parent+`'>삭제</a>
												<a href='#' class='can'>취소</a>
												<a href='#' class='mod'>수정</a>
											</div>                
							        	</article>`;       
						
						$('.commentList').append(article);
						$('#formComment > textarea[name=content]').val(''); // input창 초기화
						$('.empty').hide(); // 댓글없음 창 가리기
						
					}else {
						alert('댓글 등록이 실패했습니다.\n다시 시도해주세요.');
					}
				}
			});
		});
		
		
		// 댓글 수정
		$(document).on('click', '.mod', function(e){
			e.preventDefault();
			
			const txt = $(this).text();
			if(txt == '수정') {
				// 다른 댓글 수정 취소
				$('.mod').parent().prev().removeClass('modi');
				$('.mod').parent().prev().attr('readonly', true);
				$('.mod').text('수정');
				$('.mod').prev().hide();
				$('.mod').prev().prev().show();
				
				// 수정 모드 전환
				$(this).parent().prev().addClass('modi');
				$(this).parent().prev().attr('readonly', false); 
				$(this).parent().prev().focus(); 
				$(this).text('수정완료');
				$(this).prev().show();
				$(this).prev().prev().hide();
				
			}else {
				// 수정 완료
				//$(this).closest('form').submit(); Form이 없으므로 jsonData 처리
				
				const content = $(this).parent().prev().val();
				const no      = $(this).prev().prev().data('no');
				
				console.log('no : ' + no);
				console.log('content : ' + content);
				
				const jsonData = {
					"kind": "MODIFY",
					"no": no,
					"content": content
				}
				
				$.ajax({
					url: '/Farmstory2/board/comment.do',
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data) {
						// 응답 값에 따른 결과 처리
						if(data.result > 0) {
							//alert('댓글이 수정되었습니다.');
						}
					}
				});
				
				// 수정 모드 해제
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).text('수정');
				$(this).prev().hide();
				$(this).prev().prev().show();
			}
			
			// 댓글 수정 취소
			$('.can').click(function(e){
				e.preventDefault();
				
				// 수정 모드 해제
				$(this).parent().prev().removeClass('modi');
				$(this).parent().prev().attr('readonly', true);
				$(this).hide();
				$(this).prev().show();
				$(this).next().text('수정');
			});
		});
	});

</script>
<jsp:include page="./_aside${group}.jsp"/>
			<section class="view">
			    <h3>글보기</h3>
			    <input type="hidden" name="articleNo" value="${article.no}">
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="${article.title}" readonly/></td>
			        </tr>
			        <c:if test="${article.file > 0}">
			        <tr>
			            <td>첨부파일</td>
			            <td>
			                <img src="../images/floppy_disc_icon.png" alt="fileIcon">
			                <a href="/Farmstory2/board/fileDownload.do?fno=${article.fileDto.fno}" class="downloadZone">${article.fileDto.oriName}</a>
			                <span>[ ${article.fileDto.download}회 다운로드 ]</span>
			            </td>
			        </tr>
			        </c:if>
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly>${article.content}</textarea>
			            </td>
			        </tr>
			    </table>
			    <div>
			    	<c:if test="${sessUser.uid eq article.writer || sessUser.role eq 'ADMIN'}">
			        <a href="${ctxPath}/board/delete.do?group=${group}&cate=${cate}&no=${article.no}" class="btnDelete">삭제</a>
			        <a href="${ctxPath}/board/modify.do?group=${group}&cate=${cate}&no=${article.no}" class="btnModify">수정</a>
			        </c:if>
			        <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
			    </div>
			    
			    <!-- 댓글리스트 -->
			    <section class="commentList">
			        <h3>댓글목록</h3>
			        <c:forEach var="comment" items="${comments}">
			        <article class="comment">
						<span class="nick">${comment.nick}</span>
						<span class="date">${comment.rdate}</span>
						<textarea name="comment" readonly>${comment.content}</textarea>
		             
						<div>
							<c:if test="${sessUser.uid eq comment.writer}">
							<a href="#" class="del" data-no="${comment.no}" data-pno="${article.no}">삭제</a>
							<a href="#" class="can" data-no="${comment.no}">취소</a>
							<a href="#" class="mod" data-no="${comment.no}">수정</a>
							</c:if>
						</div>                
			        </article>
			        
			        </c:forEach>
			        <c:if test="${empty comments}">
			        <p class="empty">등록된 댓글이 없습니다.</p>
			        </c:if>
			        
			    </section>
			
			    <!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <h3>댓글쓰기</h3>
			        <form id="formComment" action="#" method="post">
			        	<input type="hidden" name="parent" value="${no}">
            			<input type="hidden" name="writer" value="${sessUser.uid}">
			            <textarea name="content"></textarea>
			            <div>
			                <a href="#" class="btn btnCancel">취소</a>
			                <input type="submit" id="btnComment" class="btn btnComplete btnWrite" value="작성완료"/>
			            </div>
			        </form>
			    </section>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>