<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>

	// success 알림창
	const success = ${success};
	
	if(success == 100){
		alert('일치하는 회원 정보가 없습니다.\n아이디, 비밀번호를 다시 확인해주세요.');
		
	}else if(success == 101){
		alert('로그인 후 이용할 수 있는 서비스입니다.\n로그인 후 이용해주세요.');
		
	}else if(success == 102){
		alert('관리자 계정만 접근 할 수 있는 페이지입니다.');
		
	}else if(success == 200){
		alert('성공적으로 회원가입 되었습니다.');
		
	}else if(success == 300){
		alert('비밀번호가 성공적으로 변경되었습니다.');
	}

</script>
        <div id="user">
            <section class="login">
        		<form action="${ctxPath}/user/login.do" method="post">
		        	<input type="hidden" name="no" value="no"/>
		            <table border="0">
		                <tr>
		                    <td><img src="${ctxPath}/images/login_ico_id.png" alt="아이디"></td>
		                    <td><input type="text" name="uid" placeholder="아이디 입력"></td>
		                </tr>
		                <tr>
		                    <td><img src="${ctxPath}/images/login_ico_pw.png" alt="비밀번호"></td>
		                    <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
		                </tr>
		            </table>
		            <input type="submit" value="로그인" class="btnLogin">
		        </form>
		        
		        <div>
		            <h3>회원 로그인 안내</h3>
		            <p>
		                아직 회원이 아니시면 회원으로 가입하세요.
		            </p>
		            <a href="${ctxPath}/user/terms.do">회원가입</a>
		        </div>
		        
		    </section>
        </div>
<%@ include file="../_footer.jsp" %>