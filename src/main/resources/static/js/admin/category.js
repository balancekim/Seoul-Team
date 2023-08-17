/**
 * 
 */

 $(function(){
	$('.edit-cate').click(editClick);
	$('.edit-cancel').click(cancelClick);
})

function editClick(){
	var udwrap=$(this).parents(".udwrap");
	udwrap.find(".update").hide();
	udwrap.find('.form-update').show();
}

function cancelClick(){
	var udwrap=$(this).parents(".udwrap");
	udwrap.find(".update").show();
	udwrap.find('.form-update').hide();
}
