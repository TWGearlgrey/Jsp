<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	
	pageContext.include("./_aside"+group+".jsp");
%>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="#" method="post">
			        	<input type="hidden" name="no" value="">
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" value="제목입니다."/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content">내용입니다.</textarea>                                
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td>
			                    <td><input type="file" name="file"/></td>
			                </tr>
			            </table>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="수정완료">
			            </div>
			        </form>
			    </article>
			</section>
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>