$(".myItem").click(function(){
    this.className="myItem focus";
    $(".Item")[0].className="Item";
    $(".waitItem")[0].className="waitItem";
    $(".myItem_show")[0].className="myItem_show active";
    $(".Item_show")[0].className="Item_show";
    $(".waitItem_show")[0].className="waitItem_show";
});
$(".waitItem").click(function(){
    this.className="waitItem focus";
    $(".Item")[0].className="Item";
    $(".myItem")[0].className="myItem";
    $(".waitItem_show")[0].className="waitItem_show active";
    $(".Item_show")[0].className="Item_show";
    $(".myItem_show")[0].className="myItem_show";
});
$(".Item").click(function(){
    this.className="Item focus";
    $(".waitItem")[0].className="waitItem";
    $(".myItem")[0].className="myItem";
    $(".Item_show")[0].className="Item_show active";
    $(".waitItem_show")[0].className="waitItem_show";
    $(".myItem_show")[0].className="myItem_show";
});
 

