$(function () {
    $("a.ConfirmDelete").click(function () {
        /*
        * 这里有个this对象，表示当前所在的标签元素。
        * 通过一次parent可获取上级标签，再parent，便是上一个标签的上一个
        * 这里的意思是，通过标签a找到标签td，再找到tr，通过tr找到其第一个td
        * 再获取第一个td就能获取其名字
        * */
        return confirm("你确定要删除"+ $(this).parent().parent().find("td:first").text()+"吗？")
    });
    $("#PageThrow").click(function () {
        var pageNo=parseInt($("#Page_input").val());
        var pageTotal=parseInt($("#pageTotal").val());
        if (pageNo>0&&pageNo<=pageTotal){
            window.location.href="manager/bookServlet?action=page&pageNo="+pageNo;
        }else{
            confirm("你输入的页数不合法！");
        }
    });
});