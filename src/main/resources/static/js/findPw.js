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