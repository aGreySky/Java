window.onload=function(){
  $(".student").css({"width":"100px","height":"100px","margin":"-50px"});
  setTimeout(function () {
    $(".teacher").css({"width":"100px","height":"100px","margin":"-50px"});
    setTimeout(function () {
      $(".mannager").css({"width":"100px","height":"100px","margin":"-50px"});
      $("a").css("opacity","1");
    }, 100);
  }, 100);
}
