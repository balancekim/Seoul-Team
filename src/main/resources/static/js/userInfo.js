let check_pw = (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/); // 비밀번호 유효성 검사 (영문 및 숫자 4-20글자)
var check =false
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
function checkPw(){
	var pw=$("#password").val()
	if(!check_pw.test(pw)){
		alert("숫자,영문자,특수문자를 포함한 8~20자리를 적어주세요")
		
	}
}
function checkPw2(){
	var pw=$("#password").val()
	var pw2=$("#passwordCheck").val()
	if(pw != pw2){
		alert("비밀번호가 다릅니다.")
		check=false
	}
}

function updateSubmit(){
	
	if((pw==pw2 && (pw==""|| pw==null))){
		return true
	}
	
	var pw=$("#password").val()
	
	if(!check_pw.test(pw) ){
		alert("숫자,영문자,특수문자를 포함한 8~20자리를 적어주세요")
		$("#password").val("");
		$("#password").focus();
		return false;
	}
	var pw2=$("#passwordCheck").val()
	if(pw != pw2){
		alert("비밀번호를 확인해주세요.")
		$("#passwordCheck").val("");
		$("#passwordCheck").focus();
		return false;
	}
	

        	  
	
}
