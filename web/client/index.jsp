<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 黄渝斌
  Date: 2021/8/2
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>前台展示</title>
    <base href="http://localhost:8081/HBook/">
    <link rel="stylesheet" type="text/css" href="css/book_displayCss.css">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/client_indexJs.js"></script>
</head>
<body>
    <div class="header">
        <h1>前台展示</h1>
        <div class="loginOrRegister">
            <c:if test="${empty sessionScope.username}">
                <a href="static/Login.jsp">登录</a>
                |
                <a href="static/Register.jsp">注册</a>
            </c:if>
            <c:if test="${not empty sessionScope.username}">
                <a href="client/mainIndex.jsp">${sessionScope.username}的主页</a>
            </c:if>
        </div>
        <%@include file="/common/manager_menu.jsp"%>
    </div>
    <div class="indexBox" >
        <form action="client/clientBookServlet" method="get">
            <input type="hidden" name="action" value="PriceSearch">
            <label for="FirstPrice" >价格:</label>
            <input type="text" name="FirstPrice" id="FirstPrice" value="${param.FirstPrice}">
            <label for="EndPrice">-</label>
            <input type="text" name="EndPrice" id="EndPrice" value="${param.EndPrice}">
            <input type="submit" value="搜索">
        </form>
    </div>
    <div class="MainBox" >
        <div class="count">
            <p id="totalCount"></p>
            <p id="cartings"></p>
        </div>
        <form class="CartForm">
                <input type="hidden" name="action" value="addItem">
                    <c:forEach items="${requestScope.page.items}" var="book">
                        <table class="list">
                        <tr class="img" >
                            <td colspan="2"><img src="${book.img}" alt="找不到图片"></td>
                        </tr>
                        <tr class="book_name">
                            <td class="label">书名:</td>
                            <td class="name">${book.name}</td>
                        </tr>
                        <tr class="book_author">
                            <td class="label">作者:</td>
                            <td class="name">${book.author}</td>
                        </tr>
                        <tr class="book_price">
                            <td class="label">价格:</td>
                            <td class="name">${book.price}</td>
                        </tr>
                        <tr class="book_sales">
                            <td class="label">销量:</td>
                            <td class="name">${book.sales}</td>
                        </tr>
                        <tr class="book_stock">
                            <td class="label">库存:</td>
                            <td class="name">${book.stock}</td>
                        </tr>
                        <tr >
                            <td colspan="2">
                                <%--
                                    这个隐藏域的问题，不要用id属性，id是唯一的，下一个就不行了
                                    所以用name属性，传给form抱歉，并且每次获取新的id值
                                --%>
<%--                                <input type="hidden" class="bookId" name="bookId" value="${book.id}">--%>
                                <input type="submit" bookId="${book.id}" class="addCart" value="添加至购物车"/>
                            </td>
                        </tr>
                    </table>
                    </c:forEach>
        </form>
<%--        </form>--%>
    </div>
    <%@ include file="/common/PagingNavigation.jsp"%>
    <%@include file="/common/foot.jsp"%>
</body>
</html>
