<%@page import="kr.farmstory1.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String pg   = request.getParameter("pg");
	
	if(type == null){
		type = "0";
	}

	ProductDAO dao = new ProductDAO();
	
	// 페이지 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산
	if(pg != null){
		currentPage = Integer.parseInt(pg);
	}
	
	// Limit 시작값 계산
	start = (currentPage - 1) * 10;
	
	// 전체 상품 갯수
	total = dao.selectCountProductsTotal(type);
	
	// 페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	List<ProductDTO> products = dao.selectProducts(type, start);
%>
<div id="sub">
    <div><img src="../images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="../images/sub_aside_cate2_tit.png" alt="장보기"/>

            <ul class="lnb">
                <li class="on"><a href="./list.jsp">장보기</a></li>
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
                <a href="./list.jsp?type=0" class="<%= type.equals("0") ? "on":"" %>">전체<%= type.equals("0")?"("+total+")":""%> |</a>
                <a href="./list.jsp?type=1" class="<%= type.equals("1") ? "on":"" %>">과일<%= type.equals("1")?"("+total+")":""%> |</a>
                <a href="./list.jsp?type=2" class="<%= type.equals("2") ? "on":"" %>">야채<%= type.equals("2")?"("+total+")":""%> |</a>
                <a href="./list.jsp?type=3" class="<%= type.equals("3") ? "on":"" %>">곡류<%= type.equals("3")?"("+total+")":""%></a>
            </p>
            <table border="0">
            	<% for(ProductDTO product : products){ %>
                <tr>
                    <td>
                        <a href="./view.jsp"><img src="/Farmstory1/thumb/<%= product.getThumb1() %>" class="thumb" alt="사과 500g"></a>
                    </td>
                    <td>
						<%
                    		switch(product.getType()){
                    			case 1: out.print("과일"); break;
                    			case 2: out.print("야채"); break;
                    			case 3: out.print("곡물"); break;
                    		}
                    	%>
					</td>
                    <td><a href="#"><%= product.getpName() %></a></td>
                    <td><strong><%= product.getPriceWithComma() %></strong>원</td>
                </tr>
                <% } %>
            </table>

            <div class="paging">
	        	<% if(pageGroupStart > 1){ %>
	            <a href="./list.jsp?type=<%= type %>&pg=<%= pageGroupStart - 1 %>" class="prev">이전</a>
	            <% } %>
	            
	            <% for(int i=pageGroupStart ; i<=pageGroupEnd ; i++){ %>
	            <a href="./list.jsp?type=<%= type %>&pg=<%= i %>" class="num <%= (currentPage == i)?"current":"" %>"><%= i %></a>
	            <% } %>
	            
	            <% if(pageGroupEnd < lastPageNum){ %>
	            <a href="./list.jsp?type=<%= type %>&pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
	            <% } %>
	        </div>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>