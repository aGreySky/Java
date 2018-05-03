
// layer 格式
const infoLayer = {
  icon: 2,
  time: 2000
}
const successLayer = {
  icon: 1,
  time: 2000
}

// 字符切换
function htmlEncode(str) {
  str = str.replace("<", "&lt;");
  str = str.replace(">", "&gt;");
  str = str.replace("&", "&amp;");
  str = str.replace("'", "&apos;");
  str = str.replace('"', "&quot;");
  return str;
}
function htmlDecode(str) {
  str = str.replace("&lt;", "<");
  str = str.replace("&gt;", ">");
  str = str.replace("&amp;", "&");
  str = str.replace("&apos;", "'");
  str = str.replace("&quot;", '"');
  return str;
}

//  处理键盘backspace回退情况
function doKey(e){
var ev = e || window.event;//获取event对象
var obj = ev.target || ev.srcElement;//获取事件源
var t = obj.type || obj.getAttribute('type');//获取事件源类型
if(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea"){
 return false;
 }
 }
 
//禁止后退键 作用于Firefox、Opera
document.onkeypress=doKey;
//禁止后退键  作用于IE、Chrome
document.onkeydown=doKey;

//将JSON格式中的日期字符（如：/Date(1303348560000+0800)/）转换为日期格式（如：2014-03-15）
function ConvertJSONDateToJSDateObject(JSONDateString) {
  var date = new Date(parseInt(JSONDateString.replace("/Date(", "").replace(")/", ""), 10));
  var y = date.getFullYear();
  var m = date.getMonth()+1;
  var d = date.getDate();
  return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
}

//将JSON格式中的日期字符（如：/Date(1303348560000+0800)/）转换为日期格式（如：2014-03-15）
function ConvertJSONDateToJSSpecificDateObject(JSONDateString) {
  var date = new Date(parseInt(JSONDateString.replace("/Date(", "").replace(")/", ""), 10));
  var y = date.getFullYear();
  var m = date.getMonth()+1;
  var d = date.getDate();
  var h=date.getHours();
  var mi = date.getMinutes();
  var s = date.getSeconds();
  return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d)+"  "+(h < 10 ? "0" + h : h)+":"+(mi < 10 ? "0" + mi : mi)+":"+(s < 10 ? "0" + s : s);
}
// HTML中调用示例：  ConvertJSONDateToJSDateObject(result[i].发布日期)

/*当日期 pubDate 与今天的天数差值 小于 days 天数时，返回一个 new.gif 图片*/
function ShowNewIcon(pubDate, days) {
  var date = new Date(parseInt(pubDate.replace("/Date(", "").replace(")/", ""), 10));
  var now = new Date();

  var DateS = new Date(date.getFullYear(), date.getMonth(), date.getDate());
  var DateE = new Date(now.getFullYear(), now.getMonth(), now.getDate());
  var diff = parseInt(Math.abs(DateS - DateE) / 1000 / 60 / 60 / 24)/*把相差的毫秒数转换为天数*/
  if (diff < days) {
    return "&nbsp;<img src='/common/images/new.gif' border='0'/>"
  } else {
    return "";
  }
}

//将分钟数转化为xx时xx分形式
function minuteTohour(time) {

  var hour = Math.floor(parseInt(time) / 60);
  (hour==0) ? (hour='00') : hour.toString()
  var minutes = parseInt(time) % 60;
  (minutes>=0 && minutes<10) ? (minutes='0'+minutes.toString()) : minutes.toString()
  var timeString = hour + ':' + minutes + ':00';
  return timeString;
};

//将秒数转化为xx时xx分xx秒形式
function secondTohour(time) {

  var hour = Math.floor(parseInt(time) / 3600);
  (hour==0) ? (hour='00') : hour.toString()
  var minutes = Math.floor((parseInt(time) - 3600 * hour)  / 60);
  (minutes>=0 && minutes<10) ? (minutes='0'+minutes.toString()) : minutes.toString()
  var seconds = parseInt(time) - 3600 * hour - 60 * minutes;
  (seconds>0 && seconds<10) ? (seconds='0' + seconds.toString()) : seconds.toString()

  var timeString = hour + ':' + minutes + ':' + seconds;
  return timeString;
};

// 获得参数
function getParameter(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if(r!=null)
  return  unescape(r[2]); 
  else{
    return null;
  }
}
// 获得父窗口参数
function getParentParameter(name) {
  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
  var r = window.parent.location.search.substr(1).match(reg);
  if(r!=null)
  return  unescape(r[2]); 
  else{
    return null;
  }
}


// 导航切换
$('.header-ul li:not(.header-ul-info)').click(function() {
  $(this).addClass('active');
  $(this).siblings().removeClass('active');
  layer.closeAll();
});

// ajax加载动画
function showLoding() {
  $(document).ajaxStart(function () {
    layer.load(2);  
  }),
  $(document).ajaxStop(function () {
    layer.closeAll();
  });
}

// Ajax
const Ajax = function (url, method, data, success) {
  $.ajax({
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
    error: function () {
      layer.msg('访问失败', infoLayer);
    }
  });
}

// 获取当前课程信息
function GetCourseInfor() {
  Ajax(
    "/Common/GetCourse", // 选择一门课程（获取获取Session中当前课程）
    "get",
    {},
    function (data) {
      // 获取课程成功 -- 进入下页
      if (data.status == 1000) {
        $('#courses-now-id').text(data.data.课程ID);
        $('#courses-now-title').text(data.data.课程名称);
      }
      // 有错误发生 -- 提示即可
      if (data.status == 1001) {
        layer.msg('没有可以操作的课程', infoLayer);
        return false;
      }
    }
  );
}

// 获取教师信息
function GetLoginTeacherInfo() {
  Ajax(
    "/Teacher/GetLoginTeacherInfo",  // 获取已登录教师信息
    "get",
    {},
    function (data) {
      // 获取信息成功 -- 通过验证，可以操作
      if (data.status == 1000) {
        $('#teacher-ip').text(data.data.登录IP);
        $('#teacher-id').text(data.data.教师ID);
        $('#teacher-name').text(data.data.姓名);
      }
      // 没有登录，或登录过期 -- 跳转到登录页
      if (data.status == 1001) {
        window.top.location.href = "login.html";
        return false;
      }
    }
  );
}

//获取已登录学生信息
function GetLoginStudentInfo() {
  Ajax(
    "/Student/GetLoginStudentInfo",  // 获取已登录学生信息
    "get",
    {},
    function (data) {
      // 获取信息成功 -- 通过验证，可以操作
      if (data.status == 1000) {
        $('#stu-img').append(
          "<img src='images/head/" + data.data.照片 + "' alt='stu-img' class='responsive-img center-align'>"
        );
        $('#stu-ip').text(data.data.登录IP);
        $('#stu-id').text(data.data.学生ID);
        $('#stu-name').text(data.data.姓名);
      }
      // 没有登录，或登录过期 -- 跳转到登录页
      if (data.status == 1001) {
        window.top.location.href = "login.html";
        return false;
      }
    }
  );
}

//学生可以操作的课程信息
function GetStudentCourses() {
  Ajax(
    "/Common/GetCourse", // 选择一门课程
    "get",
    {
      courseID: $(this).find(".courses-id").text()  // 课程 ID 或无参(直接获取 Session 中的课程)
    },
    function (data) {
      // 获取课程成功 -- 进入下页
      if (data.status == 1000) {
        $('#courses-now-title').text(data.data.课程名称);
      }
      // 有错误发生 -- 提示即可
      if (data.status == 1001) {
        layer.msg('没有可以操作的课程', infoLayer);
        return false;
      }
    }
  );
}

// 显示课程信息
// GetCourseInfor();
// // 显示教师信息
// GetLoginTeacherInfo();


// //显示课程信息
// GetStudentCourses();
// // 显示学生信息
// GetLoginStudentInfo();


// 通用退出登陆
$('#commonLogout').click(function() {
  $.ajax({
    url: "/Common/Logout",  // 退出登录
    type: "post",
    async: false,
    cache: false,
    dataType: "json",
    success: function (data) {
      // 安全退出 -- 跳转到系统首页
      if (data.status == 1000) {
        window.top.location.href = "/index.html";
      }
    },
    error: function () {
      layer.msg('访问失败', infoLayer);
    }
  });
});


