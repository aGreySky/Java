//确认删除
function ifRemove(){
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
}

//确认清空
function ifClear(){
    if (!confirm("确认要清空？")) {
        window.event.returnValue = false;
    }
}

//删除成功提示
function remove(){
	//从地址栏读取flag的值
	var flag='';
	var url=window.location.search;
	if(url.indexOf("?")!=-1){   
	 	var str = url.substr(1);
  		var strs=str.split("&");   
 		for(i=0;i<strs.length;i++){   
    		if([strs[i].split("=")[0]]=='flag') 
    			flag=unescape(strs[i].split("=")[1]);
    		else
    			flag=null;
 		}
	}
 	//flag为真，删除商品成功
 	if(flag=="true"){
 		alert("移除闲置成功!");
 	}
 	if(flag=="false"){
 		alert("移除闲置失败，请重试!");
 	}
 }

//添加闲置进购物车
function add(){
	//从地址栏读取flag的值
	var flag='';
	var url=window.location.search;
	if(url.indexOf("?")!=-1){   
	 	var str = url.substr(1);
  		var strs=str.split("&");   
 		for(i=0;i<strs.length;i++){   
    		if([strs[i].split("=")[0]]=='flag') 
    			flag=unescape(strs[i].split("=")[1]);
    		else
    			flag=null;
 		}
	}
	 	
 	//flag为真，添加商品成功
 	if(flag=="true"){
 		alert("添加闲置成功!");
 	}
 	if(flag=="false"){
 		alert("物品已经在购物车里了!");
 	}
 }

//修改闲置信息
function update(){
	//从地址栏读取flag的值
	var flag='';
	var url=window.location.search;
	if(url.indexOf("?")!=-1){   
	 	var str = url.substr(1);
  		var strs=str.split("&");   
 		for(i=0;i<strs.length;i++){   
    		if([strs[i].split("=")[0]]=='flag') 
    			flag=unescape(strs[i].split("=")[1]);
    		else
    			flag=null;
 		}
	}
	 	
 	//flag为真，添加商品成功
 	if(flag=="0"){
 		alert("修改信息成功!");
 	}
 	if(flag=="1"){
 		alert("权限不足，修改信息失败!");
 	}
 	if(flag=="2"){
 		alert("图片仅支持jpg,jpeg,png格式，请重新上传!");
 	}
 	if(flag=="3"){
 		alert("数据格式出错，请重新输入!");
 	}
 	if(flag=="4"){
 		alert("服务器出错了!");
 	}
 }
//添加闲置信息
function addGoods(){
	//从地址栏读取flag的值
	var flag='';
	var url=window.location.search;
	if(url.indexOf("?")!=-1){   
	 	var str = url.substr(1);
  		var strs=str.split("&");   
 		for(i=0;i<strs.length;i++){   
    		if([strs[i].split("=")[0]]=='flag') 
    			flag=unescape(strs[i].split("=")[1]);
    		else
    			flag=null;
 		}
	}
	 	
 	//flag为真，添加商品成功
 	if(flag=="0"){
 		alert("添加商品成功!");
 	}
 	if(flag=="1"){
 		alert("数据不能为空!");
 	}
 	if(flag=="2"){
 		alert("图片仅支持jpg,jpeg,png格式，请重新上传!");
 	}
 	if(flag=="3"){
 		alert("数据格式出错，请重新输入!");
 	}
 	if(flag=="4"){
 		alert("服务器出错了!");
 	}
 	if(flag=="5"){
 		alert("您已经添加了类似的闲置，不能重复添加!");
 	}
 }

