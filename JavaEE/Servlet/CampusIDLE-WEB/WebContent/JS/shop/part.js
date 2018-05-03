function Parts(){
	var div='';
	var url=window.location.search;
	if(url.indexOf("?")!=-1){   
	  var str = url.substr(1);
	}
	  var strs=str.split("&");   
	  for(i=0;i<strs.length;i++){   
	    if([strs[i].split("=")[0]]=='div') 
	    	div=unescape(strs[i].split("=")[1]);
	  }   
	if(div==2){
		$(".Item")[0].className="Item";
		$(".waitItem")[0].className="waitItem focus";
		$(".myItem")[0].className="myItem";
	    $(".waitItem_show")[0].className="waitItem_show active";
	    $(".Item_show")[0].className="Item_show";
	    $(".myItem_show")[0].className="myItem_show";
	}else if(div==3){
		$(".Item")[0].className="Item";
		$(".myItem")[0].className="myItem focus";
		$(".waitItem")[0].className="waitItem";
		$(".myItem_show")[0].className="myItem_show active";
		$(".Item_show")[0].className="Item_show";
		$(".waitItem_show")[0].className="waitItem_show";
	}
}