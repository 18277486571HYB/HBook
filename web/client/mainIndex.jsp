<%--
  Created by IntelliJ IDEA.
  User: 黄渝斌
  Date: 2021/7/27
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8081/HBook/">
    <link rel="stylesheet" href="css/menuCss.css">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/menuJs.js"></script>
</head>
<body>
<div class="mainIndex">
    <%@include file="/common/menu.jsp"%>
    <%--
        注意，这里的manager不是因为有一个包叫manager所以才加上的，这是自定义的，只要和web.xml
        文件里的一样就可以了。
        而后面为何要加上？action=list
        在前面的用户模块有提及，我们在表单中放一个隐藏域，并且设置action属性方便我们抽取出baseDao
        这里也一样，只不过在后台管理的首页是没有表单提交的，所以我们只能预先设置。
    --%>
    <a href="manager/bookServlet?action=page">后台管理</a>
</div>
<%@include file="/common/foot.jsp"%>
</body>
</html>
