let check_pw = (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/); // 비밀번호 유효성 검사 (영문 및 숫자 4-20글자)
var check =false
var emailCheck =false;
var mail=false;
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

function checkmail(){

	 var emailBox = $('#userEmail').val();
	
	if (emailBox == '' || emailBox == null) {
    	 mail=false;  
   				 } 
	  $.ajax({
        beforeSend: function (xhr) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           xhr.setRequestHeader(header, token);
		},
        url: '/email-check',
        type: 'post',
        data: {email: emailBox},
        success: function (result) {
            if (result === "false") {
                alert("이메일이 존재하지 않습니다.");
                emailCheck= true;
                mail=true;
            } else{
                 alert("이메일이 일치합니다.");
                emailCheck = false;
                mail=false;
            }
        }
    });
	 

}


function sendAut(){
	  var userEmail = $("#userEmail").val();
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");

 
if(mail==true){
 $.ajax({
    type: "POST",
    url: "/mail-auth",
    data: { userEmail: userEmail },
 	 beforeSend: function(xhr) {
      xhr.setRequestHeader(header, token);
      
    },
    success: function(result){
      alert("인증번호가 발송되었습니다");
    }
  });
}else{
	alert('이메일을 다시 입력해주세요');
}
}



