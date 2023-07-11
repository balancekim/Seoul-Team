/**
 * 
 */
$(function(){
	var def=$(".main-img").attr("value");
	$("#zoom").prop("src",def)	
})



function imgHover(target) {
    $(".img-list .img").removeClass("main-img");
    $(target).addClass("main-img");
    var imgval=$(target).attr("value");
    console.log(imgval)
    $("#zoom").prop("src",imgval)
    
}


function pay(btnTag){
	var amount=$(btnTag).sibiling(".amount").val();
	var name=$(btnTag).sibiling(".name").val();
	
	var IMP = window.IMP; // 생략 가능
	IMP.init("imp74400321");
	
	 IMP.request_pay({
      pg: "kcp",
      pay_method: "card",
      merchant_uid: "ORD"+new Date().getTime(),   // 주문번호
      name: name,
      amount: amount,                         // 숫자 타입
      buyer_email: "gildong@gmail.com",
      buyer_name: "홍길동",
      buyer_tel: "010-4242-4242",
      buyer_addr: "서울특별시 강남구 신사동",
      buyer_postcode: "01181"
    }, function (rsp) { // callback
      //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
      var msg="";
      if(rsp.success){
		  msg="결제완료";
	  }else{
		  msg="결제실패"
	  }
	  alert(msg);
    });
}