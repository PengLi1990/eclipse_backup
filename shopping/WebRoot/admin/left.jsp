<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="/css/admin/left.css" type="text/css" rel="stylesheet" />
<script src="/js/admin/jquery-1.4.2.min.js"></script>
<script src="/js/admin/left.js"></script>
<title></title>
</head>
<body>
<div class="all"><img src="../images/admin/menu_plus.gif" id="allmenu" /></div>
<div class="main">
<div class="menus">
<ul id="menu">
	<!-- <li class="title"><a href="javascript:void(0);"><b>网站设置</b></a>
	<ul class="menuhide">
		<li><img src="../images/admin/menu_arrow.gif" />
		&nbsp;<a href="javascript:void(0)" target="mainFrame">系统设置</a></li>
		<li><img src="../images/admin/menu_arrow.gif" />
		&nbsp;<a href="__URL__/user" target="mainFrame">管理员设置</a></li>
	</ul>
	</li> -->
	<li class="title"><a href="javascript:void(0)"><b>首页设置</b></a>
	<ul class="menuhide">
		<li><img src="/images/admin/menu_arrow.gif" />&nbsp;<a
			href="#" target="mainFrame">网站首页</a></li>
		<li><img src="/images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/flink" target="mainFrame">友情链接</a></li>
		<li><img src="/images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/hzqy" target="mainFrame">合作企业</a></li>
	</ul>
	</li>

	<li class="title"><a href="javascript:;" target="mainFrame"><b>商品管理</b></a>
	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="goods.action" target="mainFrame">商品列表</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="addgoods.action" target="mainFrame">添加商品</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="typelist.action" target="mainFrame">商品分类列表</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="addgoodstype.action" target="mainFrame">添加商品分类</a></li>
	</ul>
	</li>
<!--  
	<li class="title"><a href="javascript:;" target="mainFrame"><b>公司介绍</b></a>
	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/intro/act/zc" target="mainFrame">总经理致辞</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/intro" target="mainFrame">公司简介</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/intro/act/lc" target="mainFrame">发展历程</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/intro/act/ry" target="mainFrame">公司荣誉</a></li>
	</ul>
	</li>
	<li class="title"><a href="javascript:;" target="mainFrame"><b>文章管理</b></a>
	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/article" target="mainFrame">新闻列表</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/article_add" target="mainFrame">添加新闻</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/article_type" target="mainFrame">新闻分类列表</a></li>
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/article_type_add" target="mainFrame">添加新闻分类</a></li>
	</ul>
	</li>
	<li class="title"><a href="javascript:;" target="mainFrame"><b>图片管理</b></a>

	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/pic" target="mainFrame">图片列表</a></li>
	</ul>
	<li class="title"><a href="javascript:;" target="mainFrame"><b>技术支持</b></a>
	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/gbook" target="mainFrame">留言列表</a></li>
	</ul>
	</li>

	<li class="title"><a href="javascript:;" target="mainFrame"><b>在线订购</b></a>
	<ul class="menuhide">
		<li><img src="images/admin/menu_arrow.gif" />&nbsp;<a
			href="__URL__/gbook" target="mainFrame">订购列表</a></li>
	</ul>
	</li>
	<li class="title"><a href="javascript:void(0);"><b>关于XFCMS</b></a></li>
	
	-->
</ul>
</div>
</div>
</body>
</html>
