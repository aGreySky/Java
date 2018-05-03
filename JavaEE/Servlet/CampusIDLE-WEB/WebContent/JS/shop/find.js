 function Return(){
	//从地址栏读取action的值
		var action='';
		var url=window.location.search;
		if(url.indexOf("?")!=-1){   
		 	var str = url.substr(1);
	  		var strs=str.split("&");   
	 		for(i=0;i<strs.length;i++){   
	    		if([strs[i].split("=")[0]]=='action') 
	    			action=unescape(strs[i].split("=")[1]);
	    		else
	    			action=null;
	 		}
		}
		if(action=="find"){
			document.getElementById("return").style.display="block";
		}else{
			document.getElementById("return").style.display="none";
		}
 }
 
	        	
	    
   

