<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="vo.MemberVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	// 전송 데이터 수신
	String uid = request.getParameter("uid");
	
	// 데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	MemberVO vo = new MemberVO();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `member` WHERE `uid`=?");
		
		psmt.setString(1, uid);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setPos(rs.getString(4));
			vo.setDep(rs.getString(5));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h3>Member 등록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/list.jsp">member 목록</a>
		
		<form action="/Ch06/member/modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="<%= vo.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= vo.getName() %>"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= vo.getHp() %>"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option value="부장" <%= vo.getPos().equals("부장") ? "selected" : "" %>>부장</option>
							<option value="차장" <%= vo.getPos().equals("차장") ? "selected" : "" %>>차장</option>
							<option value="과장" <%= vo.getPos().equals("과장") ? "selected" : "" %>>과장</option>
							<option value="대리" <%= vo.getPos().equals("대리") ? "selected" : "" %>>대리</option>
							<option value="사원" <%= vo.getPos().equals("사원") ? "selected" : "" %>>사원</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<option value="영업1부" <%= vo.getDep().equals("영업1부") ? "selected" : "" %>>영업1부</option>
							<option value="영업2부" <%= vo.getDep().equals("영업2부") ? "selected" : "" %>>영업2부</option>
							<option value="영업3부" <%= vo.getDep().equals("영업3부") ? "selected" : "" %>>영업3부</option>
							<option value="영업4부" <%= vo.getDep().equals("영업4부") ? "selected" : "" %>>영업4부</option>
							<option value="영업5부" <%= vo.getDep().equals("영업5부") ? "selected" : "" %>>영업5부</option>
							<option value="영업지원부" <%= vo.getDep().equals("영업지원부") ? "selected" : "" %>>영업지원부</option>
							<option value="인사부" <%= vo.getDep().equals("인사부") ? "selected" : "" %>>인사부</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>