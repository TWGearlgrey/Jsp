<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");
%>
<div id="sub">
    <div><img src="../images/sub_top_tit5.png" alt="COMMUNITY"></div>
    <section class="community">
        <aside>
            <img src="../images/sub_aside_cate5_tit.png" alt="커뮤니티"/>

            <ul class="lnb">
                <li class="<%= cate.equals("notice") ? "on" : "" %>"><a href="/Farmstory1/board/list.jsp?group=Community&cate=notice">공지사항</a></li>
                <li class="<%= cate.equals("menu") ? "on" : "" %>"><a href="/Farmstory1/board/list.jsp?group=Community&cate=menu">오늘의식단</a></li>
                <li class="<%= cate.equals("chef") ? "on" : "" %>"><a href="/Farmstory1/board/list.jsp?group=Community&cate=chef">나도요리사</a></li>
                <li class="<%= cate.equals("qna") ? "on" : "" %>"><a href="/Farmstory1/board/list.jsp?group=Community&cate=qna">1:1고객문의</a></li>
                <li class="<%= cate.equals("faq") ? "on" : "" %>"><a href="/Farmstory1/board/list.jsp?group=Community&cate=faq">자주묻는질문</a></li>
            </ul>

        </aside>
        <article>
            <nav>
                <img src="../images/sub_nav_tit_cate5_<%= cate %>.png" alt="커뮤니티"/>
                <p>
                    HOME > 커뮤니티 > 
                    <% if(cate.equals("notice")) { %>
                    <em>공지사항</em>
                    <% }else if(cate.equals("menu")) { %>
                    <em>오늘의식단</em>
                    <% }else if(cate.equals("chef")) { %>
                    <em>나도요리사</em>
                    <% }else if(cate.equals("qna")) { %>
                    <em>1:1고객문의</em>
                    <% }else if(cate.equals("faq")) { %>
                    <em>자주묻는질문</em>
                    <% } %>
                </p>
            </nav>

            <!-- 내용 시작 -->