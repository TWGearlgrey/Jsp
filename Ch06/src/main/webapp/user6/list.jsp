<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user6::list</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(function(){
				
				// 문서 로딩이 끝나고 바로 AJAX 요청
				$.ajax({
					url:'./listProc.jsp',
					type:'GET',
					dataType:'json',
					success:function(data){
						
						for(let user of data) {
							
							let tr = `<tr>
										<td>\${user.uid}</td>
										<td>\${user.name}</td>
										<td>\${user.hp}</td>
										<td>\${user.age}</td>
										<td>
											<a href='./modify.jsp?uid=\${user.uid}'>수정</a>
											<a href='./delete.jsp?uid=\${user.uid}'>삭제</a>
										</td>
									  </tr>`;
									  
							$('table').append(tr);
						}
					}
				});
			});
		
		</script>
	</head>
	<body>
		<h3>User6 목록</h3>
		<a href="/Ch06/user6/register.jsp">User6 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
		</table>
	</body>
</html>