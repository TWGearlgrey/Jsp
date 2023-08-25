<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::${member.name}(${member.uid})님의 정보 수정</title>
	</head>
	<body>
		<h3>Member 수정</h3>
		<a href="/Ch11">처음으로</a>
		<a href="/Ch11/list.do">Member 목록</a>
		
		<form action="/Ch11/modify.do" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="${member.uid}"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${member.name}"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="${member.hp}"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<c:choose>
								<c:when test="${member.pos eq '사원'}"><option selected>사원</option></c:when>
								<c:otherwise><option>사원</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '대리'}"><option selected>대리</option></c:when>
								<c:otherwise><option>대리</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '과장'}"><option selected>과장</option></c:when>
								<c:otherwise><option>과장</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '차장'}"><option selected>차장</option></c:when>
								<c:otherwise><option>차장</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.pos eq '부장'}"><option selected>부장</option></c:when>
								<c:otherwise><option>부장</option></c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<c:choose>
								<c:when test="${member.dep eq '101'}"><option selected>101</option></c:when>
								<c:otherwise><option>101</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '102'}"><option selected>102</option></c:when>
								<c:otherwise><option>102</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '103'}"><option selected>103</option></c:when>
								<c:otherwise><option>103</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '104'}"><option selected>104</option></c:when>
								<c:otherwise><option>104</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${member.dep eq '105'}"><option selected>105</option></c:when>
								<c:otherwise><option>105</option></c:otherwise>
							</c:choose>
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