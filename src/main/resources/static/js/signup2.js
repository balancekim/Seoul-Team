var idCheck = false;
var emailCheck =false;
let check_id = (/^[a-zA-Z0-9]{6,12}$/); // 아이디 유효성 검사 (영문 및 숫자 6-12글자)
let check_pw = (/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/); // 비밀번호 유효성 검사 (영문 및 숫자 4-20글자)
let check_name =(/^[가-힣]{2,4}$/); //이름 한글만 허용
let check2_id=(/^[0-9]{6,12}$/); // 비밀번호 유효성 검사 (영문 및 숫자 4-20글자)
let check_email=(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z\-]{2,5}$/);
var mail=false;
// 아이디 중복 체크
function checkUserId(){



    var idBox = $('#idBox').val();

    $.ajax({
        url: '/memberIdCheck',
        type: 'post',
        data: {idBox: idBox},
        success: function (data) {
            
            if (data === "false") {
                $('#id-dup-result').html('사용 가능한 아이디입니다.');
                idCheck = true;
                if (idBox == '' || idBox == null) {
       				 $('#id-dup-result').html('아이디를 입력해주세요.');       
   				 } else if (!check_id.test(idBox)) {
      			  $('#id-dup-result').html('영문 또는 숫자를 포함 6-10자리까지 입력해주세요.');
    			}else if (check2_id.test(idBox)){
					$('#id-dup-result').html('영문 또는 숫자를 포함 6-10자리까지 입력해주세요.');
				}	
            } else {
                $('#id-dup-result').html('이미 사용하고 있는 아이디입니다.');
                idCheck = false;
            }
        },
        beforeSend: function (jqXHR) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
    });
}


function checkPass(){
	
	var user_pass = $('#passWord').val();
	
	if (!check_pw.test(user_pass)){
		$('#pwWord').html('영문 및 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.');
	} else{
		$('#pwWord').html('가능한 비밀번호입니다.');
	}
	if(user_pass ==''||user_pass==null){
		 $('#pwWord').html('비밀번호를 입력해주세요.');
	}
}

function checkPw(){
	var user_pass= $('#passWord').val();
	var checked_pass= $('#passWord2').val();
	
	
	
	if(user_pass ===  checked_pass){
		 $('#pwWord2').html('비밀번호가 일치합니다.');
		 if(checked_pass==null || checked_pass==''){
		$('#pwWord2').html('비밀번호를 확인해주세요');
	}
	}else{
		 $('#pwWord2').html('비밀번호가 불일치합니다.');
		 
	}
}


function checkName(){
	
	var user_name= $('#userName').val();
	var check_name = (/^[가-힣]{2,4}$/); //이름 한글만 허용
		if(check_name.test(user_name)){
			$('#pwWord3').html('');
		} else if(user_name ==''||user_name==null){
		 $('#pwWord3').html('이름을 입력해주세요.');
		}else if(!check_name.test(user_name)){
			$('#pwWord3').html('정확한 이름을 입력해주세요.');
		}
}

function checkmail(){

	 var emailBox = $('#userEmail').val();
	 var check_email=(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z\-]{2,5}$/);
	
	 
	  $.ajax({
        url: '/memberEmailCheck',
        type: 'post',
        data: {emailBox: emailBox},
        success: function (data) {
            if (data === "false") {
                $('#emailal').html('사용 가능한 이메일입니다.');
                emailCheck= true;
                mail=true;
                if (emailBox == '' || emailBox == null) {
       				 $('#emailal').html('이메일을 입력해주세요.');     
       				 mail=false;  
   				 } else if (!check_email.test(emailBox)) {
      			  $('#emailal').html('이메일 형식에 맞게 입력해주세요');
      			  mail=false;
    			}
            } else{
                $('#emailal').html('이미 사용하고 있는 아이디입니다.');
                emailCheck = false;
                mail=false;
            }
        },
        beforeSend: function (jqXHR) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
    });
	 

}


// 회원가입 유효성 검사

function mySubmit() {

	var check_id =(/^[a-zA-Z0-9]{6,12}$/); // 아이디 유효성 검사 (영문 및 숫자 6-12글자)
	var check_pw =(/^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/); // 비밀번호 유효성 검사 (영문 및 숫자 4-20글자)
	var check_name = (/^[가-힣]{2,4}$/); //이름 한글만 허용
	var check2_id=(/^[0-9]$/); 
	var check_email=(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z\-]{2,5}$/);
    var member_id = $('#idBox').val();
    var member_pw = $('#passWord').val();
    var member_name = $('#userName').val();
    var member_email = $('#userEmail').val();
    var email_code = $("#email-check").val();

    // 아이디 중복 체크 여부
    if (idCheck == false) {
        return false;
    }

    // 아이디 공백 확인
    if (member_id == "" || member_id == null) {
        alert("아이디를 입력해주세요");
        $('#idBox').focus();
        return false;
    }

    // 아이디 유효성 체크
    if (!check_id.test(member_id)) {
		if(check2_id.test(member_id)){
			$('#id-dup-result').html('영문 및 숫자 포함 6-10자리까지 입력해주세요.');
	        $('#idBox').val("");
	        $('#idBox').focus();
		}
		return false;
    }

    // 비밀번호 공백 확인
    if (member_pw == "" || member_pw == null) {
        alert("비밀번호를 입력해주세요");
        $('#passWord').focus();
        return false;
    }
    //비밀번호 재확인
    if(!($('#passWord').val() ===  $('#passWord2').val())){
		 alert("비밀번호를 확인해주세요");
		 $('#passWord2').focus();
		 return false;
	}

    // 비밀번호 유효성 체크
    if (!check_pw.test(member_pw)) {
        $('#pwWord').html('영문 및 숫자, 특수문자를 포함한 비밀번호를 입력해주세요.');
        $('#passWord').val("");
        $('#passWord').focus();
        return false;
    }
	
    
    // 이름 공백 확인
 	 if (member_name == "" || member_name == null) {
        alert("이름을 입력해주세요");
        $('#userName').focus();
        return false;
    }

	var check_name =(/^[가-힣]{2,4}$/); //이름 한글만 허용
	if (!check_name.test(member_name)){
		$('#pwWord3').html('이름을 다시 설정해주세요');
		$('#userName').val("");
        $('#userName').focus();
	}
	
	if(member_email == "" || member_email == null){
		alert('이메일을 입력해주세요.');
		$('#userEmail').focus();
		return false;
	}
	
	if(!check_email.test(member_email)){
		$('#emailal').html('이메일 형식에 맞게 입력해주세요');
		$('#userName').val("");
        $('#userName').focus();
	}
	
	if(email_code==null||email_code==''){
		alert("인증번호를 입력해주세요");	
        $('#email-check').focus();
        return false;
	}
	if(email_code!=authcode){
		alert("인증번호가 다릅니다");	
        $('#email-check').focus();
        return false;
	}
	
	
}