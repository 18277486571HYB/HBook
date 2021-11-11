<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <base href="http://localhost:8081/HBook/">
    <script type="text/javascript" src="js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="js/RegisterJs.js"></script>
    <link rel="stylesheet" type="text/css" href="css/RegisterCss.css">
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>新用户注册</p>
        <p>USER REGISTER</p>
    </div>
    <div class="rg_center">
        <div class="rg_form">
            <form action="UserServlet" method="post">
                <input type="hidden" name="action" value="register"/>
                <table>
                    <tr><!--label 标签的作用是当点击文字也会跳到文本输出框-->
                        <!--for属性与ID属性对应规定 label 绑定到哪个表单元素。-->
                        <td class="td_left"><label for="username">用户名</label> </td>
                        <td class="td_right"><input type="text" name="username" id="username" value="${requestScope.username}"> </td>
                        <td ><div class="RegisterAlert" id="RegisterAlert"></div></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="password">密码</label> </td>
                        <td class="td_right"><input type="password" name="password" id="password" value="${requestScope.password}"> </td>
                    </tr>
                    <tr><!--label 标签的作用是当点击文字也会跳到文本输出框-->
                        <td class="td_left"><label for="ConfirmPassword">确认密码</label> </td>
                        <td class="td_right"><input type="password" name="ConfirmPassword" id="ConfirmPassword" > </td>
                        <td><div class="RegisterAlert">${requestScope.UnconfirmPassword? "请确认密码" : requestScope.UnconfirmPassword }</div></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="email">email</label> </td>
                        <td class="td_right"><input type="text" name="email" id="email" value="${requestScope.email}"> </td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="checkcode">验证码</label> </td>
                        <td class="td_right" >
                            <input type="text" name="checkcode" id="checkcode" >
                            <img src="kaptcha.jpg" id="img_check" alt="静态验证码为：abcd">
                        </td>
                        <td>
                            <div class="RegisterAlert">${requestScope.confirm? "请输入验证码" : requestScope.confirm}</div>
                            <a id="img_alert"><i>看不清？下一张</i></a>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <input type="submit" value="注册" id="btn_sub">
                        </td>
                    </tr>
                </table>
                <p><div class="RegisterAlert"><h1>${requestScope.user}</h1></div> </p>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <input type="button" id="Return" value="返回">
    </div>
</div>
<%@include file="/common/foot.jsp"%>
</body>
</html>
