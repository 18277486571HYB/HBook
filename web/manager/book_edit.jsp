<%--
  Created by IntelliJ IDEA.
  User: 黄渝斌
  Date: 2021/7/28
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>
    <base href="http://localhost:8081/HBook/">
    <link rel="stylesheet" type="text/css" href="css/manager_bookCss.css">
</head>
<body>
    <div class="header">
        <h1>添加图书</h1>
        <%@include file="/common/manager_menu.jsp"%>
    </div>
    <div class="Main">
        <form action="manager/bookServlet" method="post">
            <input type="hidden" name="action" value="${param.method}"/>
            <input type="hidden" name="id" value="${requestScope.book.id}">
            <div>
                <table class="table_add">
                    <tr>
                        <td><label for="name">名称:</label></td>
                        <td><input type="text" name="name" id="name" value="${requestScope.book.name}"/></td>
                    </tr>
                    <tr>
                        <td><label for="price">价格:</label></td>
                        <td><input type="text" name="price" id="price" value="${requestScope.book.price}"/></td>
                    </tr>
                    <tr>
                        <td><label for="author">作者:</label> </td>
                        <td><input type="text" name="author" id="author" value="${requestScope.book.author}"/></td>
                    </tr>
                    <tr>
                        <td><label for="sales">销量:</label></td>
                        <td><input type="text" name="sales" id="sales" value="${requestScope.book.sales}"/></td>
                    </tr>
                    <tr>
                        <td><label for="stock">库存:</label></td>
                        <td><input type="text" name="stock" id="stock" value="${requestScope.book.stock}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="提交"/></td>
                    </tr>
                </table>
            </div>

        </form>
    </div>
    <%@include file="/common/foot.jsp"%>
</body>
</html>
