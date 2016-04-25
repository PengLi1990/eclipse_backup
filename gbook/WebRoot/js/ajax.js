function $(id){
	return document.getElementById(id);
}

function getXmlHttpRequest() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}
var xmlhttp = getXmlHttpRequest();
function load(){
	var oTable= $("tbl");
	xmlhttp.onreadystatechange=function(){
		oTable.innerHTML=xmlhttp.responseText;
	};
	xmlhttp.open("GET","gbook.do?a=list",true);
	xmlhttp.send();
}

function del(url)
{
	xmlhttp.onreadystatechange=function(){
		 if (xmlhttp.readyState==4 && xmlhttp.status==200){
			 load();
		 }
	};
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}


window.onload=function(){
	load();
	var oBtn = $("ok");
	oBtn.onclick=function(){
		var sUsername = $("username").value;
		var sTitle    = $("title").value;
		var sContent  = $("content").value;
		xmlhttp.onreadystatechange=function(){
			 if (xmlhttp.readyState==4 && xmlhttp.status==200){
				 load();
			 }
		};
		xmlhttp.open("GET","http://localhost:8080/gbook/gbook.do?a=save&username="+sUsername+"&title="+sTitle+"&content="+sContent,true);
		xmlhttp.send();
//		$("username").value="";
//		$("title").value="";
//		$("content").value="";
		
	};
};

