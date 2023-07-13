/**
 * 
 */
$(function(){
	$("#show a").click(titleClicked);
})

function titleClicked(event){
	event.preventDefault();
	$.get(
		$(this).attr("href"),
		function(resultMain){
			
			$("main>.wrap").html(resultMain);
		}
	);
}