function loginCheck(){
	
	var userId = $('#userId').val();
    var userPw = $('#password').val();
	
	if (userId == "" || userId == null) {
		$('#wrongmessage1').html("아이디를 입력해주세요");
        return false;
    }
   if (userPw == "" || userPw == null) {
		$('#wrongmessage2').html("비밀번호를 입력해주세요");
        return false;
    }
}

