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
    $("a.clearCart").click(function () {
        /*
        * 这里有个this对象，表示当前所在的标签元素。
        * 通过一次parent可获取上级标签，再parent，便是上一个标签的上一个
        * 这里的意思是，通过标签a找到标签td，再找到tr，通过tr找到其第一个td
        * 再获取第一个td就能获取其名字
        * */
        return confirm("你确定要清空购物车吗？")
    });

    $(".updateNum").change(function () {
        let bookId=parseInt($(this).attr("bookId")) ;
        let count= parseInt(this.value);
        if (confirm("你确定要将此数量修改为"+count)){
            window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
        }else{
            this.value=this.defaultValue;
        }

    });
    $(".add").click(function () {
        let bookId=parseInt($(this).attr("bookId")) ;
        let count= parseInt(this.value);
        count=count+1;
        window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
    });
    $(".delete").click(function () {
        let bookId=parseInt($(this).attr("bookId"));
        let count= parseInt(this.value);
        count=count-1;
        window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
    });
})