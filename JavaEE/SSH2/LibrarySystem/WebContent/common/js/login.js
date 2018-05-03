
// layer 格式
const loginLayer = {
  offset: '65px',
  area: ['348px', '66px'],
  anim: 6,
  icon: 2,
  time: 2000
}

// 验证输入合法性
var patt1 = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
function ukong(){
  var str1=$("#username").val();
  if(str1==''){
    layer.msg('用户名不为空', loginLayer);
    return false;
  }else if (patt1.test(str1)){
    layer.msg('用户名不能有非法字符', loginLayer);
    return false;
  }else {
    return true;
  }
}
function pkong(){
  var str1=$("#password").val();
  if(str1==''){
    layer.msg('密码不能为空', loginLayer);
    return false;
  }else {
    return true;
  }
}
function loginCheck(){
  if(ukong() && pkong()){
    return true;
  }else {
    return false;
  }
}

// 登陆 Ajax
const loginAjax = function (url, method, data, success) {
    $.ajax({
        type:'get',
    url: url,
    method: method,
    async: false,
    cache: false,
    dataType: "json",
    data: data,
    timeout: 20000,
    success: function(data){
      success(data);
    },
    error: function(){
      layer.msg('访问失败', loginLayer);
    },
    beforeSend: function(){
      layer.load(1);
    },
    complete: function(){
      layer.closeAll('loading');
    }
  });
}

$('document').ready(function() {
  // 监控 enter
  $("body").on("keydown", function(e) {
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
      if(loginCheck()){
        login();
      }
    }
  });

  // 点击登陆
  $('#submit-btn').click(function(){
    if(loginCheck()){
      login();
    }
  });
});
