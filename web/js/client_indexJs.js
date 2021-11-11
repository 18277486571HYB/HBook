$(function () {
    $("#PageThrow").click(function () {
        var pageNo=parseInt($("#Page_input").val());
        var pageTotal=parseInt($("#pageTotal").val());
        if (pageNo>0&&pageNo<=pageTotal){
            window.location.href="client/clientBookServlet?action=PriceSearch&FirstPrice="+parseInt($("#FirstPrice").val())+"&EndPrice="+parseInt($("#EndPrice").val())+"&pageNo="+pageNo;
        }else{
            confirm("你输入的页数不合法！");
        }
    });
    $(".addCart").click(function () {
        // var bookId=parseInt($(".bookId").val());
        // var bookId=$(this).attr("bookId");
        // window.location.href="cartServlet?action=addItem&id="+bookId;
        let bookID=$(this).attr("bookId");
        $.getJSON("http://localhost:8081/HBook/cartServlet","action=addItem&bookId="+bookID,function (data) {
            $("#totalCount").text("您购物车里共有"+data.totalCount+"件商品");
            $("#cartings").text("您刚刚将"+data.lastName+"加入到购物车中！");
        })
    });

});