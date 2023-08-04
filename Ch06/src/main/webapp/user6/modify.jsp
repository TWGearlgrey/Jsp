<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="vo.User2VO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user6::modify</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(function(){

				// 수정 데이터 요청하기
				$.get("./user.jsp?uid=<%=uid%>", function(data){
					console.log(data);
					
					$('input[name=uid]').val(data.uid);
					$('input[name=name]').val(data.name);
					$('input[name=hp]').val(data.hp);
					$('input[name=age]').val(data.age);
				});
				
				// 수정 데이터 전송하기
				$('input[type=submit]').click(function(e){
					
					// 수정 데이터 가져오기
					const uid  = $('input[name=uid]').val();
					const name = $('input[name=name]').val();
					const hp   = $('input[name=hp]').val();
					const age  = $('input[name=age]').val();
					
					// JSON 생성
					const jsonData = {
						"uid": uid,
						"name": name,
						"hp": hp,
						"age": age 
					};
					
					e.preventDefault();
					
					$.post('./modifyProc.jsp', jsonData, function(data){
						
						if(data.result >= 1) {
							alert('데이터가 수정 되었습니다.');
							location.href = './list.jsp';
						}else {
							alert('데이터 수정에 실패했습니다.');
						}
					});
				});
			});
		</script>				
	</head>
	<body>
		<h3>User6 수정</h3>
		<a href="/Ch06/user6/list.jsp">User6 목록</a>
		
		<form action="/Ch06/user6/modifyProc.jsp">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>