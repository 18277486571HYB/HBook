<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <base href="http://localhost:8081/HBook/">
    <link rel="stylesheet" href="css/cartCss.css">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/cartJs.js"></script>
</head>
<body>
<div class="header">
    <h1>购物车管理</h1>
    <%@include file="/common/manager_menu.jsp"%>
    <div class="headerFunction">
        <a class="clearCart" href="cartServlet?action=clear">清空购物车</a>
        <a href="orderServlet?action=createOrder">结账</a>
    </div>
</div>
<div class="MainBox" >
    <c:if test="${not empty sessionScope.cart.map}">
        <p class="count">您购物车有${sessionScope.cart.totalCount}本书,总金额为${sessionScope.cart.totalPrice}钱</p>
    </c:if>
    <div class="list">
        <table>
            <c:if test="${empty sessionScope.cart.map}">
                <tr>
                    <td colspan="5"><a class="alert" href="index.jsp">亲，您的购物车为空</a></td>
                </tr>
            </c:if>
            <c:if test="${not empty sessionScope.cart.map}">
                <tr>
                    <td>商品名称</td>
                    <td>数量</td>
                    <td>单价</td>
                    <td>金额</td>
                    <td colspan="2">操作</td>
                </tr>
                <c:forEach items="${sessionScope.cart.map}" var="entry">
                    <tr>
                        <td>${entry.value.name}</td>
                        <td>
                            <input type="hidden" class="bookId" value="${entry.value.id}">
                            <button bookId="${entry.value.id}" class="add" value="${entry.value.count}">+</button>
                            <label>
                                <c:if test="${entry.value.count<0}">
                                    <input bookId="${entry.value.id}" class="updateNum" style="width: 80px;" type="text" value="0">
                                </c:if>
                                <c:if test="${entry.value.count>=0}">
                                    <input bookId="${entry.value.id}" class="updateNum" style="width: 80px;" type="text" value="${entry.value.count}">
                                </c:if>
                            </label>
                            <button bookId="${entry.value.id}" class="delete" value="${entry.value.count}">-</button>
                        </td>
                        <td>${entry.value.price}</td>
                        <td>${entry.value.totalPrice}</td>
                        <td colspan="2"><a class="ConfirmDelete" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a> </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>

</div>
<%@include file="/common/foot.jsp"%>
</body>
</html>
