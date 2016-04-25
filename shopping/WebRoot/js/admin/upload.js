$(function(){
	//�ϴ�ͼƬ
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
				alert("���ϴ���ʽΪ jpg|png|bmp ��ͼƬ��");
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