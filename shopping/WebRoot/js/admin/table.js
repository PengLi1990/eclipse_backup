window.onload = function() {
	var aTr = document.getElementsByTagName("tr");
	var oldColor;
	for (i = 0; i < aTr.length; i++) {
		if (i % 2 == 1) {
			aTr[i].style.backgroundColor='#C0C0C0';
		}
		
		//JavaScript±Õ°ü
		aTr[i].onmouseover=function(){
			oldColor = this.style.backgroundColor;
			this.style.backgroundColor='#00C957';
		};	
		aTr[i].onmouseout=function(){
			this.style.backgroundColor=oldColor;
		};	
		
	}
};