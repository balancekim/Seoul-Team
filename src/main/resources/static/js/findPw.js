function checkId(){
	var id=$('#user-id').val();
	var token = $("meta[name='_csrf']").attr("content");
 	var header = $("meta[name='_csrf_header']").attr("content");
	

	
	$.ajax({
		beforeSend: function(xhr) {
     	xhr.setRequestHeader(header, token);
   		 },
		url:'/check-id',
		type:'post',
		data:{userId:id},
		success:function(result){
			if(result==="true"){
			}else{
				alert('아이디가 존재하지 않습니다.');
			}
		}
		
	});
}

function sendAuthPass(){
	var userEmail = $("#userEmail").val();
	var token = $("meta[name='_csrf']").attr("content");
 	var header = $("meta[name='_csrf_header']").attr("content");
	
	if(mail==false){
 $.ajax({
    type: "POST",
    url: "/pass-auth",
    data: { userEmail: userEmail },
 	 beforeSend: function(xhr) {
      xhr.setRequestHeader(header, token);
      
    },
    success: function(result){
      alert("임시비밀번호가 발송되었습니다");

    }
  });
}else{
	alert('이메일을 다시 입력해주세요');
}

}