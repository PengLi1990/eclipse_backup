$(function(){
	//上传图片
	new AjaxUpload('#addLabProdPic', {
		action: 'upload.action', 
		name: 'file',
		responseType: 'json',
		onSubmit : function(file , ext){
			$('#loading').css('display','block');
			if (ext && /^(jpg|png|bmp)$/.test(ext.toLowerCase())){
				this.setData({
					'fileFileName': file
				});
			} else {
				alert("请上传格式为 jpg|png|bmp 的图片！");
				return false;				
			}
		},
		onComplete : function(file,response){
			if(response.error) {
				alert("error");
				return;
			}
	
			$("#viewImgs").append("<img src='"+response.picUrl+"'/>");
			$('#loading').css('display','none');
			$("#picpath").val( $("#picpath").val()+","+ response.picUrl);
		}		
	});
});