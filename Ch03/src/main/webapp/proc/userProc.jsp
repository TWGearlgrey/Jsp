<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid  = request.getParameter("uid"); // get이 붙으면 반환이 필요
	String name = request.getParameter("name");
	String age  = request.getParameter("age");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>userProc</title>
		<%--
			1_request 연계 문서
		--%>
	</head>
	<body>
		<h3>사용자 데이터 수신</h3>
		<p>
			아이디 : <%= uid %><br>
			이　름 : <%= name %><br>
			나　이 : <%= age %><br>
		</p>
		
		<a href="../1_request.jsp">뒤로가기</a>
			
	</body>
</html>