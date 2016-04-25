<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="{$keywords}" />
<meta name="Description" content="{$description}" />
<title>XXX商城网站</title>

<!--js-->
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/common.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/ddsmoothmenu.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script src="js/jquery.elastislide.js"></script>
<script src="js/jquery.jcarousel.min.js"></script>
<script src="js/jquery.accordion.js"></script>
<script src="js/light_box.js"></script>
<script type="text/javascript">$(document).ready(function(){$(".inline").colorbox({inline:true, width:"50%"});});</script>
<!--end js-->
<!-- Mobile Specific Metas ================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>

<!-- CSS ================================================== -->

<link rel="stylesheet" href="css/styles.css"/>
<link rel="stylesheet" href="css/colors.css"/>
<link rel="stylesheet" href="css/skeleton.css"/>
<link rel="stylesheet" href="css/layout.css"/>
<link rel="stylesheet" href="css/ddsmoothmenu.css"/>
<link rel="stylesheet" href="css/elastislide.css"/>
<link rel="stylesheet" href="css/home_flexslider.css"/>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/xiaomi.css"/>


<link rel="stylesheet" href="css/light_box.css"/>
<script src="js/html5.js"></script>

</head>
<body>
	<div class="mainContainer sixteen container">
            <!--Header Block-->
            <div class="header-wrapper">
              <header class="container">
                <div class="head-right">
     
            
                  <section class="header-bottom">
                    <div class="cart-block">
					<ul>
						
						<li><a href="showcar.action" title="Cart"><img title="Item" alt="Item" src="images/item_icon.png" /></a></li>
						<li>购物车</li>
					</ul>
				</div>
                  </section>
                  </div>
                  
                  
                  
  
                <h1 class="logo"><a href="admin/index.jsp" title="Logo">
                  <img title="Logo" alt="Logo" src="images/logo.png" />
                  </a></h1>
                <nav id="smoothmenu1" class="ddsmoothmenu mainMenu">
                  <ul id="nav">
                    <li class=""><a href="index.action" >主页</a></li>
                   <s:iterator value="topTypeList"> 
							 <li class=""> 
						<a href=""> <s:property value="typename"/> </a>
						  </li> 
					 </s:iterator> 
                  </ul>
                </nav>
                
    
                
              </header>
            </div>
      <div class="module">手机 平板
  <div class="content">
    <ul class="goods_grid">
      <s:iterator value="alist">
      <li>
        <p class="img"><a href="showgoods.action?id=${id}"><img src="<s:property value="goodspic"/>" alt="${goodsname}"/></a></p>
        <p class="name"><a href="showgoods.action?id=${id}" title="${goodsname}"><s:property value="goodsname"/></a></p>
        <p class="price">
         ${price}
        </p>
      </li>
      </s:iterator>
    </ul>
  </div>电视 盒子
<div class="content">
    <ul class="goods_grid">
      <s:iterator value="blist">
      <li>
        <p class="img"><a href="showgoods.action?id=${id}"><img src="<s:property value="goodspic"/>" alt="${goodsname}"/></a></p>
        <p class="name"><a href="showgoods.action?id=${id}" title="${goodsname}"><s:property value="goodsname"/></a></p>
        <p class="price">
         ${price}
        </p>
      </li>
      </s:iterator>
    </ul>
  </div>路由器<div class="content">
    <ul class="goods_grid">
      <s:iterator value="clist">
      <li>
        <p class="img"><a href="showgoods.action?id=${id}"><img src="<s:property value="goodspic"/>" alt="${goodsname}"/></a></p>
        <p class="name"><a href="showgoods.action?id=${id}" title="${goodsname}"><s:property value="goodsname"/></a></p>
        <p class="price">
         ${price}
        </p>
      </li>
      </s:iterator>
    </ul>
  </div>配件 周边<div class="content">
    <ul class="goods_grid">
      <s:iterator value="dlist">
      <li>
        <p class="img"><a href="showgoods.action?id=${id}"><img src="<s:property value="goodspic"/>" alt="${goodsname}"/></a></p>
        <p class="name"><a href="showgoods.action?id=${id}" title="${goodsname}"><s:property value="goodsname"/></a></p>
        <p class="price">
         ${price}
        </p>
      </li>
      </s:iterator>
    </ul>
  </div>

     
</div>      
</body>
</html>