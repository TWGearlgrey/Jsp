<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 로그인 사용자 가져오기
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");

	if(sessUser == null) {
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return;
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글목록</title>
    <link rel="stylesheet" href="./css/style.css">    
</head>
<body>
    <div id="container">
        <header>
            <h3>Board System v1.0</h3>
            <p>
                <%= sessUser.getNick() %>님 반갑습니다.
                <a href="/Jboard1/user/logout.jsp" class="logout">[로그아웃]</a>
            </p>
        </header>
        <main>
            <section class="list">
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
                            <td><a href="#">테스트 제목입니다.</a>&nbsp;[3]</td>
                            <td>길동이</td>
                            <td>20-05-12</td>
                            <td>12</td>
                        </tr>
                    </table>
                </article>

                <!-- 페이지 네비게이션 -->
                <div class="paging">
                    <a href="#" class="prev">이전</a>
                    <a href="#" class="num current">1</a>                
                    <a href="#" class="num">2</a>                
                    <a href="#" class="num">3</a>                
                    <a href="#" class="next">다음</a>
                </div>

                <!-- 글쓰기 버튼 -->
                <a href="#" class="btnWrite">글쓰기</a>
            </section>
        </main>
        <footer>
            <p>ⓒcopyright sangmin.com</p>
        </footer>
    </div>
</body>
</html>