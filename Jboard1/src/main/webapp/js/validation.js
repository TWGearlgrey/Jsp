/**
 * 
 */
// 폼 데이터 검증결과 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;

// 데이터 검증에 사용하는 정규표현식
let reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
let reName  = /^[가-힣]{2,10}$/ 
let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
		
// 유효성 검증(Validation)
$(function(){
	
	// 아이디 검사
	$('input[name=uid]').keydown(function(){
		$('.resultId').text('');
		isUidOk = false;
	});
	
	// 비밀번호 검사
	$('input[name=pass2]').focusout(function(){
		 const pass1 = $('input[name=pass1]').val();
		 const pass2 = $('input[name=pass2]').val();
		 
		 if(pass1 == pass2){
			 
			 if(pass1.match(rePass)){
				 $('.resultPass').css('color', 'green').text('사용할 수 있는 비밀번호입니다.');
				 isPassOk = true;
				 
			 }else{
				 $('.resultPass').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 조합 5자리 이상이어야 합니다.');
				 isPassOk = false;
			 }
			 
		 }else{
			 $('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			 isPassOk = false;
		 }
	});
	
	// 이름 검사
	$('input[name=name]').focusout(function(){
		
		const name = $(this).val();
		
		if(name.match(reName)){
			$('.resultName').text('');
			isNameOk = true;
			
		}else {
			$('.resultName').css('color', 'red').text('유효한 이름이 아닙니다.');
			isNameOk = false;
		}
	});
	
	// 최종 전송
	$('#formUser').submit(function(e){
		
		if(!isUidOk){ // 아이디가 유효하지 않으면
			alert('아이디를 확인 하십시오.');
			return false;
		}
		
		if(!isPassOk){ // 비밀번호가 유효하지 않으면
			alert('비밀번호를 확인 하십시오.');
			return false;
		}
		
		if(!isNameOk){ // 이름이 유효하지 않으면
			alert('이름을 확인 하십시오.');
			return false;
		}
		
		if(!isNickOk){ // 별명이 유효하지 않으면
			alert('별명을 확인 하십시오.');
			return false;
		}
		
		if(!isEmailOk){ // 이메일이 유효하지 않으면
			alert('이메일을 확인 하십시오.');
			return false;
		}
		
		if(!isHpOk){ // 휴대폰이 유효하지 않으면
			alert('휴대폰번호를 확인 하십시오.');
			return false;
		}
		
		return true; 
		// return false; => 폼 전송 취소. preventDefault();와 동일한 효과 
		// return true;  => 폼 전송 시작.
		
	}); // 최종전송 끝...
	
}); // 유효성 검증 끝