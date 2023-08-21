<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1_FileUpload</title>
		<!-- 
			날짜 : 
			이름 : 
			내용 :
			
			파일 경로 : C:\Users\Administrator\Desktop
			\Workspace\Jsp\.metadata\.plugins
			\org.eclipse.wst.server.core
			\tmp1\wtpwebapps\Ch07
		 -->
	</head>
	<body>
		<h3>1. 파일 업로드 실습</h3>
		
		<form action="./proc/fileUpload.jsp" method="post" enctype="multipart/form-data">
			<input type="text" name="uid" placeholder="아이디 입력"><br>
			<input type="text" name="name" placeholder="이름 입력"><br>
			<input type="file" name="oName"><br>
			<input type="submit" value="전송">
		</form>
	</body>
</html>