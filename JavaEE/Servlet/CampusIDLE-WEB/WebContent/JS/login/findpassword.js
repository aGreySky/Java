(function(){
    var hintText={user_email:{hint:"请填写邮箱",right:"√邮箱格式正确",wrong:"×邮箱格式有误，请重新输入"},
            user_name:{hint:"请填写符合格式的用户名",right:"√用户名格式正确",wrong:"×用户名格式有误，请重新输入"},
            password:{hint:"请填写符合格式的密码",right:"√密码格式正确",wrong:"×请输入符合格式的密码"},
            repassword:{hint:"请再次输入密码",right:"√再次输入密码正确",wrong:"×两次输入不一致或密码格式不正确，请重新输入"}
           };
    //先判断浏览器类型，分别执行对应的函数，event是事件类型，func是处理函数
    var regEvent=function(node, event, func){
        if (node.addEventListener)
            node.addEventListener(event, func);
        else if (node.attachEvent)
            node.attachEvent("on" + event, func);
        else
            node["on" + event] = func;
    };
	
	var inputs=document.getElementsByClassName("input"),
    id,
    hint=document.getElementsByClassName("hint"),
    index=0,
    index1=2;
    for(var j=0;j<inputs.length;j++){
        (function(i){
            regEvent(inputs[i],"focus",function(){
                hint[i].style.visibility="visible";
                id=inputs[i].id;
            });
            regEvent(inputs[i],"blur",function(){				
               regValue(id,i);
            });
        })(j)
    }
	
    function regValue(id,i){
        var flag=false,
        input=document.getElementById(id),
        value=input.value;
        switch (id){
            case "user_name":
                flag=/^[a-zA-Z0-9_]{4,16}$/.test(value.replace(/[\u0391-\uFFE5]/g,"nn"));
                id="user_name";
                break;
            case "user_email":
                flag=/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}/.test(value);
                id="user_email";
                break;
            case "password":
                flag=/^\S{6,16}$/.test(value);
                id="password";
                break;
            case "repassword":
                flag=document.getElementById("password").value==value && value !="" && value !=null && (/^\S{6,16}$/.test(value));
                break;
            default:
                break;
        }
		
		if(value!=""&&value !=null){
			if(flag) {
				index=0;
				index1=0;
				input.className="right input";
				hint[i].className="hint hint_right";
				hint[i].innerHTML=hintText[id].right;
			}else{
				input.className="wrong input";
				hint[i].className="hint hint_wrong";
				hint[i].innerHTML=hintText[id].wrong;
				index=1;
				index1=1;
			}
		}else{
			input.className="input";
			hint[i].className="hint";
			hint[i].innerHTML=hintText[id].hint;			
			hint[i].style.visibility="hidden";
			}
    };
    
    regEvent(document.getElementById("submit"),"click",function(){
	    if(index1!=0){
	            alert("您的输入有误，请检查并重新输入！");
	    }else{
	    	$.ajax({	
					url:"findpwd",
					data:{username: $("#user_name").val(),
						useremail: $("#user_email").val(),
						password: $("#password").val()
					},
					async:false,
					type:"POST",
					dataType:'json',
					timeout:3000,
					error:function(){
						alert("服务器异常");
					},
					success:function(data){
						alert(data.res)
					}
				});   	
	    }  
       
    });  
})();