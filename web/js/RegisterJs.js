$(function () {
    $("#Return").click(function () {
        location.href="static/Login.jsp";
    });
    $("#img_alert").click(function () {
        $("#img_check").attr("src","kaptcha.jpg?d="+new Date());
    });
    $("#username").blur(function () {
        $.getJSON("http://localhost:8081/HBook/UserServlet","action=isRegister&username="+this.value,function (data) {
            if (data.legal){
                $("#RegisterAlert").text("用户名合法")
            }else{
                $("#RegisterAlert").text("用户名已存在")
            }
        })
    });
})