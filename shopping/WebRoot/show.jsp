<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
body{
	text-align:center;	
}
.main{
	margin-left:auto;
	margin-right:auto;
	background-color:#666;
	width:402px;
}
#div1{
	width:402px;
	height:300px;
/*	border:1px solid #F00;*/
}

#div1 > a{display:block;}
#div2{
	width:402px;
	height:80px;
	/*border:1px solid #00F;*/
}
.sp{
	width:100px;
	height:80px;
	float:left;
	cursor:pointer;
}
</style>
	<script src="js/admin/jquery-1.10.2.js"></script>
<script>
$(function(){
	var aImg = $("#div1 > a");
	
//	for(i=1;i<aImg.size();i++){
//		aImg[i].style.display = 'none';
//	}

	aImg.each(function(index){
		if(index!=0){
			$(this).css('display','none');
		}
	});
	
	var aDiv = $(".sp");
	var index = 0;
	
	aDiv.each(function(index){
		$(this).mouseover(function(){
			aImg.each(function(){
				$(this).css('display','none');
			});		   

			aImg[index].style.display='block';
			
			aDiv.each(function(){
				$(this).css('border','');
			});
			
			$(this).css('border','1px solid #F00');
		});
	});
});
</script>
	
  </head>
 
  <body>
  	<div class="main">
<div id="div1">
<s:iterator value="goods.picpath">
<a href=""><img src="u<s:property />" width="400" height="300"/></a>
	</s:iterator>
</div>

<div id="div2">
<s:iterator value="goods.picpath">
<div class="sp"><img src="u<s:property />" width="100" height="80"/></div>
</s:iterator>
</div>
</div>
  

	<br/>
   <s:property value="goods.goodsname"/><br/>
  	<s:property value="goods.goodsdesc" escape='0'/>
  	<br/><br/><br/><br/><br/><br/>
  	<a href="addcar.action?gid=${goods.id}&goodsname=${goods.goodsname}">加入购物车</a>
  </body>
</html>
