/*这是添加评论页面的js，作者：张羊左*/ 
$(document).ready(function () {
    $("#hwd-admin-login").unbind("click");
    $("#hwd-admin-login").bind("click",function(){
        $.post("adminLogin.action",{
            adminName :$("#adminName").val(),
            adminPassword :$("#adminPassword").val()
        },function(response){
            if(response=="成功"){
                window.location.href='adminEnterGoodsManage.action';
            }else{
                alert(response);
            }
        },"json");
    });
});