<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html public "-//w3c//dtd xhtml 1.0 transitional//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="css/admin/general.css" />
<link rel="stylesheet" href="css/sample.css">

<script type="text/javascript" src="/js/admin/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/js/admin/ajaxupload.js"></script>
<script type="text/javascript" src="/js/admin/upload.js"></script>

<script src="ckeditor/ckeditor.js"></script>


<script type="text/javascript">
var data;
function execute(){
	if(data.length<1){
		return ;
	}
		var json = eval(data);
				var str='<option>请选择...</option>';
  				for(var i=1; i<json.length; i++)
				{
					str+="<option value="+json[i].id+">"+json[i].typename+"</option>";
				}	
			/* 	$("#goodstype").append("<select id='#sectype'></select>"); */
				$("#sectype").children("option").remove();
				$("#sectype").css('display','inline');
				$("#sectype").append(str);
}
$(function(){
	var oSelect = $("#selecttype");
	oSelect.change(function(){

		$.ajax({
			type:"get",
			url:"gettype.action?tid="+oSelect.val(),	
			success: function(msg){
				data = msg;
			},
			error:function(obj,msg){
				alert("没有子分类");
				data="";
			}
			,
			complete :function(data){
				execute();
			} 
		});
		
	});
});
</script>
</head>
<body>
<div class="top">添加商品</div>
<div class="main">
<form action="savegoods.action" method="post">
<table width="98%" border="0" cellpadding="2" cellspacing="2">
	<tr>
			<td align="right">商品名称:</td><td align="left"><input name="goods.goodsname" /></td>
		</tr>
		
		<tr>
			<td align="right">商品图片:</td><td align="left">
			
			<div id="viewImgs"></div>
			<img id="loading" src="/images/loading.gif" style="display:none"/>
			<button id="addLabProdPic">选择图片</button>
			<input type="hidden" name="goods.goodspic" id="picpath"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品分类:</td><td align="left">
			<div id="goodstype">
			<select name="goods.goodstype.id" id="selecttype">
			<option value="-1">请选择分类</option>
				<s:iterator value="gtlist">
					<option value='<s:property value="id" />' >
						 <s:property value="typename" />
				    </option>
				</s:iterator>
				</select>
				<select id="sectype" style="display:none;"></select>
				<select id="thirdtype" style="display:none;"></select>
				</div>
			</td>
		</tr>
		<tr>
			<td align="right">商品价格:</td><td align="left"><input name="goods.price" /></td>
		</tr>
		
		<tr>
			<td align="right">商品数量:</td><td align="left"><input name="goods.count" /></td>
		</tr>
		<tr>
			<td align="right">商品描述:</td><td align="left">
			<textarea name="goods.goodsdesc" class="ckeditor" cols="120" id="editor1"  rows="15"></textarea></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><input type="submit" value="添加商品"/></td>
		</tr>
</table>
</form>
</div>
<div class="footer">
<jsp:include page="/admin/copyright.jsp" ></jsp:include>
</div>
</body>
</html>
