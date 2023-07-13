/**
 * 
 */
$(function(){
	
	loadNotice()		
	
});
function loadNotice(){
	
	$.ajax({
		url: "/load-notice",
		type: 'post',
		beforeSend: function (jqXHR, settings) {
           var header = $("meta[name='_csrf_header']").attr("content");
           var token = $("meta[name='_csrf']").attr("content");
           jqXHR.setRequestHeader(header, token);
		},
		success : function(response) { // 결과 성공 콜백함수
    		$("#info-list").html(response)
        }
       
    });
}			