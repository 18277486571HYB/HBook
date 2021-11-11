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
</div>
<div class="MainBox" >

    <p class="count">订单号为${sessionScope.orderId}</p>

    <div class="list">
        <table>

            <tr>
                <td colspan="5"><a class="alert" href="index.jsp">亲，您的订单为空</a></td>
            </tr>

            <tr>
                <td>日期</td>
                <td>金额</td>
                <td>状态</td>
                <td><a href="#">查看详情</a></td>
            </tr>

        </table>
    </div>

</div>
<%@include file="/common/foot.jsp"%>
</body>
</html>
