function loginCheck(){
	
    var userPw = $('#password').val();
	
	
   if (userPw == "" || userPw == null) {
		$('#wrongmessage').html("비밀번호를 입력해주세요");
        return false;
    }
    
    
}
function goHome(){
	window.location.href = '/';
}

