<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <base href="http://localhost:8081/HBook/">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/LoginJs.js"></script>
    <link rel="stylesheet" type="text/css" href="css/LoginCss.css">
</head>
<body>
    <div id="lr">
        <form action="UserServlet" method="post">
            <input type="hidden" name="action" value="login"/>
            <h1 id="h1">欢迎来到HBook书城</h1>
            <table >
                <tr>
                    <td><label for="username">用户名：</label></td>
                    <td><input type="text" name="username" id="username" value="${requestScope.username}" ><br></td>
                </tr>
                <tr>
                    <td><label for="password">密码： </label></td>
                    <td><input type="password" name="password" id="password"></td>
                </tr>
                <tr>
                    <td><input type="submit" id="login" value="登录"/></td>
                    <td><input type="button" id="register" value="注册"></td>
                </tr>
            </table>
            <p>
                <div id="alert">
                    <i>${empty requestScope.msg? "请输入用户名和密码" : requestScope.msg}</i>
                </div>
            </p>
            <p style="text-align: center">
                <i>If you don't have a user, please register first</i>
            </p>
        </form>
    </div>
    <%@include file="/common/foot.jsp"%>
</body>
</html>