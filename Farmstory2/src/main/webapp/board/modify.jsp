<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="${ctxPath}/board/modify.do" method="post" enctype="multipart/form-data">
			        	<input type="hidden" name="writer" value="${sessUser.uid}">
			        	<input type="hidden" name="group" value="${group}">
			        	<input type="hidden" name="cate" value="${cate}">
			        	<input type="hidden" name="no" value="${article.no}">
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" required value="${article.title}"/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content" required>${article.content}</textarea>
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td>
			                    <td><input type="file" name="file" value="${article.fileDto.oriName}"/></td>
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
			            </table>
			            <div>
			                <a href="./list.do?group=${group}&cate=${cate}&no=${article.no}" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="수정완료">
			            </div>
			        </form>
			    </article>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>