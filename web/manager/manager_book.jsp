<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黄渝斌
  Date: 2021/7/28
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <base href="http://localhost:8081/HBook/">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/Manager_bookJs.js"></script>
    <link rel="stylesheet" type="text/css" href="css/manager_bookCss.css">
</head>
<body>
    <div class="header">
        <h1>后台管理</h1>
        <%@include file="/common/manager_menu.jsp"%>
    </div>
    <div class="Main">
        <table class="table">
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${requestScope.page.items}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.price}</td>
                    <td>${book.author}</td>
                    <td>${book.sales}</td>
                    <td>${book.stock}</td>
                    <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update">修改</a></td>
                    <td><a class="ConfirmDelete" href="manager/bookServlet?action=delete&id=${book.id}">删除</a> </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><a href="manager/book_edit.jsp?method=add">添加</a></td>
            </tr>
        </table>
    </div>
    <%@ include file="/common/PagingNavigation.jsp"%>
    <%@include file="/common/foot.jsp"%>
</body>
</html>
