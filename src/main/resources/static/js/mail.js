var authcode=false;

function showEmailCheckedButton() {
  var userEmail = $("#userEmail").val();
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");

 
if(mail==true){
 $.ajax({
    type: "POST",
    url: "/mailauth",
    data: { userEmail: userEmail },
 	 beforeSend: function(xhr) {
      // AJAX 요청 헤더에 CSRF 토큰 추가
      xhr.setRequestHeader(header, token);
      
    },
    success: function(result){
      alert("인증번호가 발송되었습니다");
 		$("#userEmail").attr("readonly",true)
 		$('#emailchecked-button').css("display","inline");
    }
  });
}else{
	alert('이메일을 다시 입력해주세요');
}
}


function EmailcheckedButton(){
  var userEmail = $("#userEmail").val();
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");
  var code = $("#email-check").val();
	var check=$("input[type=checkbox]").prop("checked").length;
	console.log(check) 

 $.ajax({
    type: "POST",
    url: "/mailauthed",
    data: { userEmail: userEmail },
 	 beforeSend: function(xhr) {
      // AJAX 요청 헤더에 CSRF 토큰 추가
      xhr.setRequestHeader(header, token);
      
    },
    success: function(mailcode){
		authcode=mailcode
	if(code==mailcode){
		alert("인증이 완료되었습니다");		
		$("#asd123").prop("checked","checked")

	}else{
		alert("인증번호가 다릅니다");
		
	}
    },
	
  });

}
