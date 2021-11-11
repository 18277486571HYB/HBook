# HBook网络书城建设

## 架构

- JavaEE主要有三层架构，请点击链接看图：

https://i.loli.net/2021/07/23/GqyYFgIJs4X9ni5.png

- 所以在不同的层级中，可以定义不同的包
  - web层：
    - com.hyb.web/Servlet/controller
  - service层：
    - com.hyb.service  Service接口包
      - com.hyb.service.impl Service接口实现包
  - DAO持久层：
    - com.hyb.dao	接口包
      - com.hyb.dao.impl	实现包
  - JavaBean：
    - com.hyb.pojo/entity/domain/bean 
  - 测试包：
    - com.hyb.test/junit
  - 工具类：
    - com.hyb.utils
  - javaEE模块固有的web包
    - 这里默认会有一个index.jsp页面，还有一个WEB-INF包。
    - WEB-INF 里保留着我们web.xml文件。以上都是固定存在的，其次我们也可以在WEB-INF里，新建一个lib包，存放我们工程要用jar包（什么功能用到什么jar，我想都学到做工程了，没有不会的吧）。
    - 还有，在这个web包下，我们得新建一个css，js，static，img包，分别存放我们每一个页面的样式和其js，static包主要是存放除了index.jsp或者index.html文件外的静态页面文件。而img包我们主要存放相关照片。
    - 其次，我们还可以新建一些其他与web页面相关一些包。用到会提及。
- 这样我们**根据以上的包先在IDEA中建立有架构**。

## 登录和注册页面

- 架构完毕，我们首先要编写一个网站最重要的安全页面，登录和注册。

- 在模块固定的web目录下，我们将index先改为html格式。名字不要改，因为浏览器解析整个工程是首先从index.html或者index.jsp开始的。这里为还未涉及到jsp，所以先写成html静态页面。

  ```html
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
          <form action="#" method="post">
              <h1 id="h1">欢迎来到HBook书城</h1>
              <table >
                  <tr>
                      <td><label for="username">用户名：</label></td>
                      <td><input type="text" name="username" id="username" ><br></td>
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
              <p style="text-align: center">
                  <i>If you don't have a user, please register first</i>
              </p>
          </form>
      </div>
  </body>
  </html>
  ```

  - 编写完登录页面，我们得编写其css样式。

  ```css
  body{
      background-image: url("../img/LoginGirl.jpg");
      background-repeat:no-repeat ;
      background-size: cover;
      background-attachment: fixed;
  
  }
  #h1{
      font-size: 22px;
      text-align:center ;
  }
  #lr{
      width: 400px;
      height: 200px;
      background-color: rgba(0,0,0,0.05);
      position: absolute;
      left: 50%;top: 50%;
      transform: translate(-50%,-50%);
  
  }
  table{
      margin: 0 auto;
  }
  
  *{
      margin: 0;/*所有的外边距为0*/
      padding: 0;/*所有的内边距为0*/
      box-sizing: border-box;/*规定两个并排的带边框的框*/
  }
  
  ```

- 在这登录页面中，我们可以点击按钮，跳转到注册页面，所以我们还得有个注册页面，并且这个注册页面还提供返回主页面的按钮。

  ```html
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
              <form action="#" method="post">
                  <table>
                      <tr><!--label 标签的作用是当点击文字也会跳到文本输出框-->
                          <!--for属性与ID属性对应规定 label 绑定到哪个表单元素。-->
                          <td class="td_left"><label for="username">用户名</label> </td>
                          <td class="td_right"><input type="text" name="username" id="username" > </td>
                      </tr>
                      <tr>
                          <td class="td_left"><label for="password">密码</label> </td>
                          <td class="td_right"><input type="password" name="password" id="password" > </td>
                      </tr>
                      <tr><!--label 标签的作用是当点击文字也会跳到文本输出框-->
                          <td class="td_left"><label for="ConfirmPassword">确认密码</label> </td>
                          <td class="td_right"><input type="password" name="ConfirmPassword" id="ConfirmPassword" > </td>
                      </tr>
                      <tr>
                          <td class="td_left"><label for="email">email</label> </td>
                          <td class="td_right"><input type="text" name="email" id="email"}"> </td>
                      </tr>
                      <tr>
                          <td class="td_left"><label for="checkcode">验证码</label> </td>
                          <td class="td_right">
                              <input type="text" name="checkcode" id="checkcode" >
                              <img src="image/verify_code.jpg" id="img_check" alt="静态验证码为：abcd">
                          </td>
                      </tr>
                      <tr>
                          <td colspan="2" style="text-align: center">
                              <input type="submit" value="注册" id="btn_sub">
                          </td>
                      </tr>
  
                  </table>
              </form>
          </div>
      </div>
      <div class="rg_right">
          <input type="button" id="Return" value="返回">
      </div>
  </div>
  </body>
  </html>
  ```

  - 注册页面的css

  ```css
  body{
      background-image: url("../img/RegisterGirl.jpg");
      background-repeat:no-repeat ;
      background-size: cover;
      background-attachment: fixed;
  }
  .rg_layout{
      width: 900px;
      height: 500px;
  
      background-color: white;
      margin: auto;
      background-color: rgba(0,0,0,0.05);
  }
  .rg_left{
      float: left;
      margin: 15px;
  }
  .rg_left>p:first-child{
      color: #FFD026;
      font-size: 20px;
  }
  .rg_left>p:last-child{
      color: #A6A6A6;
      font-size: 20px;
  }
  
  .rg_center{
      float: left;
  }
  
  .rg_right{
      float: right;
      margin: 15px;
      padding-left: 50px;
  }
  #Return{
      background-color: aliceblue;
  }
  .td_left{
      width: 100px;
      text-align: right;
      height: 45px;
  }
  .td_right{
      padding-left: 50px;
  }
  #username,#password,#email,#name,#tel,#birthday,#checkcode,#ConfirmPassword{
      width: 251px;
      height: 32px;
      border: 1px solid #A6A6A6;
      /*设置边框圆角*/
      border-radius: 5px;
      padding-left: 10px;
  }
  #checkcode{
      width: 110px;
  }
  #img_check{
      height: 32px;
      vertical-align: middle;/*设置图片的位置垂直居中*/
  }
  #btn_sub{
      width: 100px;
      height: 40px;
      background-color: #FFD026;
      border: 1px solid #FFD026;
      padding-left: 10px;
  }
  
  ```

  - 注册css样式单是上网模仿而来的，有些是多余，自行检查。

- 进行两个页面的编写，那么就要将两个页面联系起来，首先是注册页面的返回按钮。

  ```js
  $(function () {
      $("#Return").click(function () {
          location.href="Login.jsp";
      });
  })
  ```

- 其次是登录页面的注册功能。

  ```
  $(function () {
      $("#register").click(function () {
          window.location.href="static/Register.jsp"
      });
  })
  ```

- 这里还剩下登录页面的登录功能，还要注册页面的注册功能，这里我们先不着急，因为要涉及到数据库。

## 代码流程

### 数据库

- 用户有一张表，id，用户名，密码。

```mysql
create database hyb

use hyb

create table user(
	id int auto_increment primary key ,
	username varchar(20) not null unique,
	password varchar(40) not null,
	email varchar(250)
)

insert into user(username,password,email) values
('hyb','hyb','468949484@qq.com')
select * from user
```

- 创建好用户表后，我们要进行相应的javabean创建。

### 编写JDBCUtils

- com.hyb.utils 下用于连接数据库和关闭连接

```java
package www.hyb.untils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource source=null;
    static {
        try {
            Properties ps = new Properties();
            FileInputStream fs=new FileInputStream("E://javaStudy//HBook//src//Druid.properties");
            ps.load(fs);
            source=  DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
//    获取数据库连接池的连接
    public static Connection connect(){
        try {
            return source.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
//    关闭数据库连接池的连接
    public static Connection close(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
```

### 测试JDBCUtils

```java
package www.hyb.test;

import org.junit.Test;
import www.hyb.untils.JdbcUtils;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void testConnection(){
        Connection connect = JdbcUtils.connect();
        System.out.println(connect);
        JdbcUtils.close(connect);
//        com.mysql.cj.jdbc.ConnectionImpl@a514af7
    }
}
```

### 编写BaseDao

- 在com.hyb.dao.impl下，该类用于数据库的增删改查

```java
package www.hyb.dao.impl;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import www.hyb.untils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    
    private final QueryRunner queryRunner=new QueryRunner();
    
//    更新插入删除
    public int update(String sql,Object ... arg){
        Connection connect = JdbcUtils.connect();
        try {
             return queryRunner.update(connect,sql,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(connect);
        }
        return -1;
    }

//    查询一个javabean
    public <T> T queryForOne(Class<T> type,String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        BeanHandler<T> bh= new BeanHandler<T>(type);
        try {
            return queryRunner.query(connect,sql,bh,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(connect);
        }
        return null;
    }

//    查询某个值
    public Object queryOneValue(String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        ScalarHandler<Object> objectScalarHandler = new ScalarHandler<>();
        try {
            return queryRunner.query(connect,sql,objectScalarHandler,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(connect);
        }
        return null;
    }

//    查询多个
    public <T> List<T> queryList(Class<T> type,String sql,Object ...arg){
        Connection connect = JdbcUtils.connect();
        BeanListHandler<T> bh= new BeanListHandler<T>(type);
        try {
            return queryRunner.query(connect,sql,bh,arg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(connect);
        }
        return null;
    }
}
```

### UserDao接口

- 规范用户登录注册操作

```java
package www.hyb.dao;

import www.hyb.pojo.user;

public interface UserDao {

    /*首先我们是注册，当我们注册的时候会要保存用户基本信息，而用户这个基本信息就放在了pojo包下的user中*/
    public int saveUser(user u);

    /*其次便是我们登陆*/
    /*首先是用户名确认*/
    public user queryUserName(String username);
    /*其次便是密码确认*/
    public user queryUserPassword(String username,String password);

}
```

### UserDaoImpl 类

- 继承BaseDao获取其查询数据库的函数。
- 实现接口，检验登录或者注册操作。

```java
package www.hyb.dao.impl;

import www.hyb.dao.UserDao;
import www.hyb.pojo.user;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public int saveUser(user u) {
        /*保存数据相当于插入一条数据*/
        String sql="insert into user(username,password,email) values(?,?,?)";
        return update(sql,u.getUsername(),u.getPassword(),u.getEmail());
    }

    @Override
    public user queryUserName(String username) {
        /*我们要验证的是是否存在该用户，所以我们得选择器所有的列*/
        String sql="select id,username,password,email from user where username=?";
        /*sql语句的username是属性名，而queryOne里的username是user类中的实例化对象的username，是一个具体值*/
        return queryForOne(user.class,sql,username);
    }

    @Override
    public user queryUserPassword(String username, String password) {
        /*返回一个javabean，所以所有属性都有*/
        String sql="select id,username,password,email from user where username=? and password=?";
        return queryForOne(user.class,sql,username,password);
    }
}
```

### UserDao测试类

- 检验注册的时，是否保留了一个用户，若保留了，数据库里有相应的表
- 检验用户名和密码是否正确。

```java
package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.UserDaoImpl;
import www.hyb.pojo.user;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        user user = new user(null,"hfy","hfy","2751265792@qq.com");
        userDao.saveUser(user);
//        数据表插入一个数据
    }

    @Test
    public void queryUserName() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserName("hyb"));
//        user{id=1, username='hyb', password='hyb', email='468949484@qq.com'}
    }

    @Test
    public void queryUserPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserPassword("hyb", "hyb"));
    }
}
```

### UserService接口

- 登录和注册和用户名合法检验的业务接口

```java
package www.hyb.service;

import www.hyb.pojo.user;

public interface UserService {
    /*前面我们规范了UserDao，该接口你会发现就是一个保存用户数据
    * 一个是验证用户名
    * 一个是根据用户名验证密码
    * 但是，你会发现这只是一些零散的功能，而UserService接口出现，将他们整合起来*/

//    注册接口
    public void RegisterUser(user u);

//    登录接口
    public user Login(user u);

//    注册的时候，验证用户名是否合法
    public boolean isLegal(String username);
}
```

### UserServletImpl 实现类

- 实现UserService

```java
package www.hyb.service.impl;

import www.hyb.dao.UserDao;
import www.hyb.dao.impl.UserDaoImpl;
import www.hyb.pojo.user;
import www.hyb.service.UserService;

public class UserServiceImpl implements UserService {

    /*UserService实现类进行登录注册还有用户名合法验证的实现*/

//    要操作数据库，而操作数据库的接口和类在UserDao里
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void RegisterUser(user u) {
        userDao.saveUser(u);
    }

    @Override
    public user Login(user u) {
        return userDao.queryUserPassword(u.getUsername(),u.getPassword());
    }

    @Override
    public boolean isLegal(String username) {
        return userDao.queryUserName(username) == null;
    }
}
```

### UserServiceTest测试类

```java
package www.hyb.test;

import org.junit.Test;
import www.hyb.pojo.user;
import www.hyb.service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserServiceImpl userService = new UserServiceImpl();
    user user = new user(null,"hlf","hlf","468949484@qq.com");
    @Test
    public void registerUser() {

        userService.RegisterUser(user);
    }

    @Test
    public void login() {
        System.out.println(userService.Login(user));
    }

    @Test
    public void isLegal() {
        System.out.println(userService.isLegal("hlf"));
    }
}
```

## Servlet

- 在我们自定义的web包下，要编写一些Servlet程序去进行注册功能和登录功能。

- 下面举例只举例注册功能：

  - 首先，新建一个注册的Servlet程序。

    ```
    package www.hyb.web;
    import www.hyb.pojo.user;
    import www.hyb.service.UserService;
    import www.hyb.service.impl.UserServiceImpl;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    public class RegisterServlet extends HttpServlet {
    
        private final UserService userService=new UserServiceImpl();
    
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //        获取请求参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("ConfirmPassword");
            String email = request.getParameter("email");
            String checkcode = request.getParameter("checkcode");
    //        验证码是否错误
            if ("abcd".equalsIgnoreCase(checkcode)){
    //            检验用户名是否合法
                if (userService.isLegal(username)){
    //                合法就判断确认密码是否正确
    //
                    if(password.equals(confirmPassword)){
                        userService.RegisterUser(new user(null,username,password,email));
                    }else{
                       	//跳回注册页面
                        request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
                    }
    
    
                }else {
                    //跳回注册页面
                    request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
                }
            }else {
                //跳回注册页面
                request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
            }
        }
    
    }
    ```

  - 编写完Servlet程序的代码后，配置相关web.xml文件。

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0">
        <servlet>
            <servlet-name>RegisterServlet</servlet-name>
            <servlet-class>www.hyb.web.RegisterServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>RegisterServlet</servlet-name>
            <url-pattern>/RegisterServlet</url-pattern>
        </servlet-mapping>
    </web-app>
    ```

  - 编写完毕后，我们要将自定义的文件路径写到注册页面的action属性中，这样在提交表单的时候，注册的Servlet才会处理。

- 登录实现基本一样，只是登录成功后要求跳转到菜单页面，下面会提及。

## jsp

- 学习了jsp，就将所有的html页面修改成jsp页面，注意：必须加上jsp头标签，不然可能会出现编码错误。
- 修改以后，要修依赖其的一些路径后缀名。
- 在iDEA中，ctrl+shift+r可以快速搜索和修改。

### 抽取

- 所谓抽取，就是要将所有html页面中共有的一类代码，放在不同的jsp文件中，到时候在相应的jsp页面可以直接拥一个标签便可将这些代码包含进来，极大地方便了工程维护和修改。

- 首先，我们会想到，每个页面都会有页脚，而这些页脚在工程中几乎都是一样的，用于存放作者的基本信息，但是我们还没有页脚，可以先建一个页脚的jsp文件。但在存放这个页脚之前，我们得在固定的web包下，新建一个common包，表示抽取jsp的文件包，在common包里，便可以新建一个页脚的jsp文件了。

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: 黄渝斌
    Date: 2021/7/27
    Time: 11:42
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Title</title>
      <base href="http://localhost:8081/HBook/">
      <link rel="stylesheet" type="text/css" href="css/footCss.css">
  </head>
  <body>
      <div id="foot">
          <footer>
              <p><i>open source from 尚硅谷书城;@Author <a href="#"> 梵高的猪</a></i></p>
          </footer>
      </div>
  </body>
  </html>
  ```

- 有了这个页脚文件后，我们得编写其css样式单，将其美化起来。

  ```css
  #foot{
      text-align: center;
      width: 100%;
      height: 100px;
      position: fixed;
      bottom: 0;
      background-color: rgba(0,0,0,0.05);
  }
  ```

### 菜单

- 我们学会了抽取，就可以快速得做出菜单页面了。

- 首先，菜单功能一般很多jsp页面都需要的，所以我们新建一个共同jsp。

  ```jsp
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
  </head>
  <body>
  <div class="mainIndex">
      <%@include file="/common/menu.jsp"%>
  </div>
  </body>
  </html>
  ```

- 最后将其页脚的内容也包裹进来，挡在div标签后。

  ```jsp
  <%@include file="/common/foot.jsp"%>
  ```

- 最后，菜单功能实现，我们就要去修改登录的Servlet程序。

  ```java
  package www.hyb.web;
  
  import www.hyb.pojo.user;
  import www.hyb.service.UserService;
  import www.hyb.service.impl.UserServiceImpl;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class LoginServlet extends HttpServlet {
      private final UserService userService=new UserServiceImpl();
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取参数
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          if (userService.Login(new user(null,username,password,null))!=null){
              request.getRequestDispatcher("/static/mainIndex.jsp").forward(request,response);
          }else
              request.getRequestDispatcher("/Login.jsp").forward(request,response);
          }
      }
  
  }
  ```

- 最后，整个菜单页面和登录的链接，便如期实现。

## EL

- 这是对登录和注册的功能的优化，使用EL表达式可以对用户一些不规范行为进行警告。

- 前面我们学习过jsp，知道jsp就是一个动态的Servlet程序，在jsp页面里我们可以编写动态的html页面。
- 而EL表达式便是对jsp的简化。

- 这里我们举例用户名或密码不正确的行为提示是如何实现的。

  - 首先，在登录的Servlet程序中，实现接受错误的功能。

    ```java
    package www.hyb.web;
    
    import www.hyb.pojo.user;
    import www.hyb.service.UserService;
    import www.hyb.service.impl.UserServiceImpl;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    public class LoginServlet extends HttpServlet {
        private final UserService userService=new UserServiceImpl();
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //        获取参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (userService.Login(new user(null,username,password,null))!=null){
                request.getRequestDispatcher("/static/mainIndex.jsp").forward(request,response);
            }else {
                //用户或者密码错误，我们要将这些行为记录在request域中，
                //这些行为就包括用户名或密码错误这句话，还有用户名的保留。
                //注意，按照平常，这些我们密码是不保留的。
                request.setAttribute("msg","用户名或密码错误！");
                request.setAttribute("username",username);
                request.getRequestDispatcher("/Login.jsp").forward(request,response);
            }
        }
    
    }
    ```

  - 修改后，我们要在相应的标签中获取request域数据。

    - 我们可以将用户名或密码错误放在一个提醒的地方，然后用EL表达式获取request域数据。

      ```jsp
      <p>
          <div id="alert">
              <i>${empty requestScope.msg? "请输入用户名和密码" : requestScope.msg}</i>
          </div>
      </p>
      ```

      - 这里自然也可以为该提醒编写css样式。

    - 然后，我们要求返回页面后保留用户名，就要在输入用户名的input标签里获取保存好的request域数据。

      ```jsp
      <td><input type="text" name="username" id="username" value="${requestScope.username}" ><br></td>
      ```

- 至于其他一些的基本提醒实现，用EL表达式都可以轻松做到。

## 优化

### 优化一

- 在开发当中，用户的登录和注册一般都放在一个Servlet中。

- 要实现这样的功能，首先要在这个Servlet中判别是登录还是注册的请求，所以我们可以通过获取相同参数不同值来确定是注册还是登录。

- 这里，我们要分别在登录和注册的功能提交的form中，分别加入一个隐藏的input，属性name同样，value却不一样，就能区别开来。

  Register.jsp中form标签第一行

  ```jsp
  <input type="hidden" name="action" value="register"/>
  ```

  index.jsp中的form标签的第一行

  ```jsp
  <input type="hidden" name="action" value="login"/>
  ```

- 其次，要分别将两个form标签中action属性的路径改为自定义访问路径，这个路径要和xml文件里的一致。

  ```jsp
  <form action="UserServlet" method="post">
  ```

- 最后便是编写UserServlet程序。

  ```java
  package www.hyb.web;
  
  import com.sun.deploy.net.HttpRequest;
  import com.sun.deploy.net.HttpResponse;
  import www.hyb.pojo.user;
  import www.hyb.service.UserService;
  import www.hyb.service.impl.UserServiceImpl;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class UserServlet extends HttpServlet {
      private final UserService userService=new UserServiceImpl();
  
      /*实现注册功能的代码*/
      private void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
          //        获取请求参数
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String confirmPassword = request.getParameter("ConfirmPassword");
          String email = request.getParameter("email");
          String checkcode = request.getParameter("checkcode");
  //        验证码是否错误
          if ("abcd".equalsIgnoreCase(checkcode)){
  //            检验用户名是否合法
              if (userService.isLegal(username)){
  //                合法就判断确认密码是否正确
  //
                  if(password.equals(confirmPassword)){
                      userService.RegisterUser(new user(null,username,password,email));
                     	request.setAttribute("user","注册成功！");
                      request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
                  }else{
                      request.setAttribute("username",username);
                      request.setAttribute("password",password);
                      request.setAttribute("email",email);
                      request.setAttribute("UnconfirmPassword","密码不一致");
                      request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
                  }
  
  
              }else {
                  request.setAttribute("failed","用户名已存在！");
                  request.setAttribute("password",password);
                  request.setAttribute("email",email);
                  request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
              }
          }else {
              request.setAttribute("confirm","验证码错误！");
              request.setAttribute("username",username);
              request.setAttribute("password",password);
              request.setAttribute("email",email);
              request.getRequestDispatcher("/static/Register.jsp").forward(request,response);
          }
      }
  
      /*实现登录功能*/
      private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取参数
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          if (userService.Login(new user(null,username,password,null))!=null){
              request.getRequestDispatcher("/static/mainIndex.jsp").forward(request,response);
          }else {
              System.out.println("用户名或密码错误！");
              request.setAttribute("msg","用户名或密码错误！");
              request.setAttribute("username",username);
              request.getRequestDispatcher("/Login.jsp").forward(request,response);
          }
      }
  
  
  
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String action = request.getParameter("action");
          if ("register".equals(action)){
              register(request,response);
          }else if("login".equals(action)){
              login(request,response);
          }
      }
  
  }
  ```

- 编写完后，编写web.xml文件路径

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
  
      <servlet>
          <servlet-name>UserServlet</servlet-name>
          <servlet-class>www.hyb.web.UserServlet</servlet-class>
      </servlet>
  
      <servlet-mapping>
          <servlet-name>UserServlet</servlet-name>
          <url-pattern>/UserServlet</url-pattern>
      </servlet-mapping>
  </web-app>
  ```

### 优化二

- 我们可以思考，登录和注册都是用户来做的，可以归类为用户模块，而在版本的更新中，用户模块还有很多功能，比如密码修改，用户名修改，或者其他用户信息的修改等等，这里要是我们像优化一一样来写程序，无疑显得很冗杂，因为实在太多属性值，这样要写很多ifelse语句来判断。

- 使用反射来获取属性值，这样不用做任何判别是哪个属性值执行哪个功能。

```java
        String action = request.getParameter("action");
//        if ("register".equals(action)){
//            register(request,response);
//        }else if("login".equals(action)){
//            login(request,response);
//        }
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
```

### 优化三

- 优化二里提到，用户模块会有很多功能，所以我们用反射形式可大大缩减代码量，但是一个工程不仅有用户模块，也有可能是其他模块呢？这样写的反射，等到达一定量的时候，不也是很冗杂？
- 所以，我们还可以继续优化，将反射的方法放进父类里，这个父类是抽象的，让其子类继承父类就可以了。

- BaseServlet：

  ```java
  package www.hyb.web;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  import java.lang.reflect.InvocationTargetException;
  import java.lang.reflect.Method;
  
  public abstract class BaseServlet extends HttpServlet {
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String action = request.getParameter("action");
          try {
              Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
              method.invoke(this, request, response);
          } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
              e.printStackTrace();
          }
      }
  }
  ```

- 因为是公有的Servlet，所以该Servlet不用编写web.xml文件。

## BeanUtils

- 在前面注册的代码我们可以看到

```java
    //        获取请求参数
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("ConfirmPassword");
    String email = request.getParameter("email");
    String checkcode = request.getParameter("checkcode");
```

- 在大工程的开发中，若是用这种方法获取参数，未免太过冗杂，所以，可以通过第三方工具去使用，使用第三方工具之前，要实现导入三个包：commons-beanutils-1.9.4.jar，commons-logging-1.2.jar，commons-collections-3.2.2.jar 记住，这最后这个包，一定要找版本对应或者更低的，因为到后面的版本，commons里就会删除一些东西，在这里不适用。

- 导入jar包后，请看以下代码：

  ```java
  try {
      user user = new user();
      BeanUtils.populate(user,request.getParameterMap());
      System.out.println(user);
  } catch (IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
  }
  ```

- 可以看到，第三方jar包提供了一个获取全部参数的方法，但这些参数是javabean中的参数，且要和标签里的一致。用了这个方法后，我们就避免了很复杂的操作，可以直接通过产生的对象去获取其相应的属性。

- 写到这，其实还可以进行优化，既然这个方法都要写，何不将其封装在工具类中。

  ```java
  package www.hyb.untils;
  
  import org.apache.commons.beanutils.BeanUtils;
  
  import javax.servlet.http.HttpServletRequest;
  import java.lang.reflect.InvocationTargetException;
  
  public class bean {
      public static void getParameterMap(HttpServletRequest r,Object bean){
          try {
              BeanUtils.populate(bean,r.getParameterMap());
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```

  这样我们就可以在具体的功能使用得更加简洁了！

  ```java
  user user = new user();
  bean.getParameterMap(request,user);
  ```

- 但这还不行，反正我们都是为了得到这个user对象的，还不如直接返回user对象！

  ```java
  
  
  public class bean {
      public static Object getParameterMap(HttpServletRequest r,Object bean){
          try {
              BeanUtils.populate(bean,r.getParameterMap());
          } catch (Exception e) {
              e.printStackTrace();
          }
          return bean;
      }
  }
  ```

  那么在具体使用就可以更加简洁了！

  ```java
  user user= (user) bean.getParameterMap(request, new user());
  ```

- 但这样也不行，因为这还需要转型，所以我们是否可以将转型也去掉，简单，用泛型就可以了！

  ```java
  
  
  public class bean {
      public static<T> T getParameterMap(HttpServletRequest r,T bean){
          try {
              BeanUtils.populate(bean,r.getParameterMap());
          } catch (Exception e) {
              e.printStackTrace();
          }
          return bean;
      }
  }
  ```

  这样我们就可以更加简洁了！

  ```java
  user user= bean.getParameterMap(request, new user());
  ```

- 但这样还是不够，虽然已是最简洁了，我们还要考虑耦合度问题。在我们传入的参数中，是不是太过受限制了？因为不仅一个业务需要javabean，很多业务都需要javabean，但是不是那么多业务需要HttpServletRequest r 的！

  在这里，我们可以这样考虑，既然r.getParameterMap()明显是获得一个Map，那么我们直接传入一个Mp就可以了，到具体用的时候再确定是否要用HttpServletRequest r！

  ```java
  
  
  public class bean {
      public static<T> T getParameterMap(Map p, T bean){
          try {
              BeanUtils.populate(bean,p);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return bean;
      }
  }
  ```

  到时候就更加简洁了！

  ```java
  user user= bean.getParameterMap(request.getParameterMap(), new user());
  ```

## 图书模块

- 以上都是用户模块，下面是进行图书模块。

### 数据库

- 创建数据库表

```sql
create table book(
	id int primary key auto_increment,
	name varchar(100),
	price decimal(11,2),
	author varchar(100),
	sales int,
	stock int,
	img_path varchar(255)
)
insert into book(id,name,price,author,sales,stock,img_path) 
values(null,'完美世界','200.00','辰东','100000','15000','img/Main_PerfectWorld.jpg')

select * from book
```

### 页面

- 有了这张数据库表，我们先创建后台管理的jsp页面。

- 首先是后台管理会有主菜单页面。

  ```java
  <%--
    Created by IntelliJ IDEA.
    User: 黄渝斌
    Date: 2021/7/27
    Time: 19:29
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <div>
      <a href="#">我的订单</a><br/>
      <a href="#">返回</a><br/>
      <a href="#">注销账号</a><br/>
  </div>
  ```

- 其次便是后台管理的主页面，拥有罗列全部现有上架图书功能。并且该主页面提供主菜单，每条数据都拥有修改和删除功能，其次最主要还要有添加新图书的功能。

  ```jsp
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
              
              <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><a href="#">添加</a></td>
              </tr>
          </table>
      </div>
      <%@include file="/common/foot.jsp"%>
  </body>
  </html>
  ```

- 其次是主页面样式单。

  ```css
  body{
      background-color: white;
  }
  .header{
      text-align: center;
      /*border: red solid;*/
  }
  
  
  .Main{
      text-align: center;
      border: black solid;
      width: 60%;
      height: 60%;
      position: absolute;
      left: 50%;top: 50%;
      transform: translate(-50%,-50%);
      overflow: auto;
  }
  .table{
      text-align: center;
      width: 100%;
  }
  .table_add{
      text-align: center;
      margin: 0 auto;
      width: 50%;
      height: 50%;
      position: absolute;
      left: 50%;top: 50%;
      transform: translate(-50%,-50%);
  }
  
  #alert{
      text-align: center;
      color: red;
  }
  ```

### javaBean

```java
package www.hyb.pojo;

import java.math.BigDecimal;

public class book {
    private Integer id;
    private String name;
    private String author;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String img="img/PerfectWorld.jpg";

    public book() {
    }

    public book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
//        因为如果用EL表达式可能产生空串，所以我们要避免这路径为空
        if (img!=null&&!"".equals(img)){
            this.img = img;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        if (img!=null&&!"".equals(img)){
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return "book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", img='" + img + '\'' +
                '}';
    }
}
```

### DAO

#### 接口

```java
package www.hyb.dao;

import www.hyb.pojo.book;

import java.util.List;

public interface BookDao {
//    添加书籍
    public int addBook(book book);
//    删除书籍
    public int delBookById(Integer id);
//    更新书籍
    public int updateBook(book book);
//    查询书籍
    public book queryBook(Integer id);
//    查询多本书籍
    public List<book> queryBooks();
}
```

#### 实现类

```java
package www.hyb.dao.impl;

import www.hyb.dao.BookDao;
import www.hyb.pojo.book;

import java.util.List;

/*
* 注意：这个BaseDao是前面抽取的BaseDao
* */

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(book book) {
        String sql="insert into book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg());
    }

    @Override
    public int delBookById(Integer id) {
        String sql="delete from book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(book book) {
        String sql="update book set name=?,author=?,price=?,sales=?,stock=?,img_path=?where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg(),book.getId());
    }

    @Override
    public book queryBook(Integer id) {
        String sql="select id,name,author,price,sales,stock,img_path from book where id=?";
        return queryForOne(book.class,sql,id);
    }

    @Override
    public List<book> queryBooks() {
        String sql="select id,name,author,price,sales,stock,img_path from book";
        return queryList(book.class,sql);
    }
}
```

### service

#### 接口

```java
package www.hyb.service;

import www.hyb.pojo.book;

import java.util.List;

public interface BookService {
    /*该Service是用户看见的层级，而DAO是我们对数据库操作的层级*/

    public void addBook(book book);

    public void delBook(Integer id);

    public void updateBook(book book);

    public book queryBook(Integer id);

    public List<book> queryBooks();

}

```

#### 实现类

```java
package www.hyb.service.impl;

import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.pojo.book;
import www.hyb.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao=new BookDaoImpl();

    @Override
    public void addBook(book book) {
        bookDao.addBook(book);
    }

    @Override
    public void delBook(Integer id) {
        bookDao.delBookById(id);
    }

    @Override
    public void updateBook(book book) {
        bookDao.updateBook(book);
    }

    @Override
    public book queryBook(Integer id) {
        return bookDao.queryBook(id);
    }

    @Override
    public List<book> queryBooks() {
        return bookDao.queryBooks();
    }
}
```

### Servlet

- Servlet提供后台管理各种功能的实现。添加，修改，删除

```java
package www.hyb.web;

import www.hyb.pojo.book;
import www.hyb.service.BookService;
import www.hyb.service.impl.BookServiceImpl;
import www.hyb.untils.bean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
```

### 添加图书

- 添加图书页面。

```jsp
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
        <form action="#" method="post">
            <input type="hidden" name="action" value="add"/>
            <div>
                <table class="table_add">
                    <tr>
                        <td><label for="name">名称:</label></td>
                        <td><input type="text" name="name" id="name"/></td>
                    </tr>
                    <tr>
                        <td><label for="price">价格:</label></td>
                        <td><input type="text" name="price" id="price"/></td>
                    </tr>
                    <tr>
                        <td><label for="author">作者:</label> </td>
                        <td><input type="text" name="author" id="author"/></td>
                    </tr>
                    <tr>
                        <td><label for="sales">销量:</label></td>
                        <td><input type="text" name="sales" id="sales"/></td>
                    </tr>
                    <tr>
                        <td><label for="stock">库存:</label></td>
                        <td><input type="text" name="stock" id="stock"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="添加"/></td>
                    </tr>
                </table>
            </div>

        </form>
    </div>
    <%@include file="/common/foot.jsp"%>
</body>
</html>
```

- 点击后台管理首页的添加链接，跳转到添加图书页面，所以首先要在后台管理首页中加入添加图书的连接。

```jsp
<td><a href="manager/book_edit.jsp">添加</a></td>
```

- 其次，便是添加页面，点击添加，提交表单，提交到bookServlet中。

```jsp
<form action="manager/bookServlet" method="post">
```

- 在添加页面中，我们隐藏域的值为add，所以我们为bookServlet中add 编写相应的Servlet

```java
public class BookServlet extends BaseServlet {
    private final BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        *注意：可能会出现乱码问题，可以在BaseServlet的post方法设置编码为UTF-8
        * */

//        获取请求参数，利用之前封装好的工具类。
        book parameterMap = bean.getParameterMap(request.getParameterMap(), new book());

        /*
        下面要防止添加空串，这里我们只要不允许书名和作者为空便可以了
        * */
        if (parameterMap.getName()!=null&&parameterMap.getAuthor()!=null){
            //        将图书信息保存到数据库中
            bookService.addBook(parameterMap);

            //        添加成功，跳转到列表页面
//        request.getRequestDispatcher("/manager/manager_book.jsp").forward(request,response);
            /*注意：以上方法不太安全，若是用户不小心按到了
             * F5快捷键，则会刷新页面，重新开始请求，则会操作数据的二次添加。
             * */

//        这里我们使用请求重定向解决

            response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
        }else{
            request.getRequestDispatcher("/manager/book_edit.jsp").forward(request,response);
        }

    }

}
```

### 图书列表

- 图书添加完毕，后台首页会显示所有图书信息，也就是数据库该有多少就有多少。

```java
    /*
    * 展示所有图书到后台管理首页中
    * */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询全部图书
        List<book> books = bookService.queryBooks();
//        将全部图书保存到request域中
        request.setAttribute("books",books);
//        请求转发到/manager/manager_book.jsp
        request.getRequestDispatcher("/manager/manager_book.jsp").forward(request,response);
    }
```

- 编写完罗列的Servle，应该在后台管理首页接受信息。

  前面我们在后台管理首页中的jsp代码的表格中只有两行，一行是表头，一行是表尾巴，所以这里的信息我们插在中间。

```jsp
<c:forEach items="${requestScope.books}" var="book">
    <tr>
        <td>${book.name}</td>
        <td>${book.price}</td>
        <td>${book.author}</td>
        <td>${book.sales}</td>
        <td>${book.stock}</td>
        <td><a href="#">修改</a></td>
        <td><a href="#">删除</a> </td>
    </tr>
</c:forEach>
```

### 删除图书

- 删除图书无需新的页面，但是要单击事件，防止用户大意操作，删除错误。
- 但首先我们要在后台管理主页面的jsp中，连接该文件的js文件，还要在删除标签中，加入类型名，方便绑定标签。

```jsp
<td><a class="ConfirmDelete" >删除</a> </td>
```

```js
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
});
```

- 绑定完毕后，我们可以进行具体功能的编写，首先是编写好地址。

- 这里的地址编写和此后台管理首页的地址类似。

  此后台管理首页：

  ```jsp
  manager/bookServlet?action=list
  ```

  通过这个地址，能让服务器通过action的值快速定位Servlet中的方法，这里我们为实现删除功能，也可以这么做。

  我们可以将删除的地址写成以下“

  ```jsp
  manager/bookServlet?action=delete
  ```

  但到了这里还不够，前面我们编写过删除的service实现类，知道我们删除的函数是根据书在数据库中id来删除的，所以这里我们还要传个参数过去，最终结果写成

  ```jsp
  <td><a class="ConfirmDelete" href="manager/bookServlet?action=delete&id=${book.id}">删除</a> </td>
  ```

- 编写完这个标签，我们就可以去编写Servlet的delete删除方法。

```java
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取id
        String id = request.getParameter("id");
        /*
        * 可见以上获取的id是String类型的，而我们本身的id是Integer类型的，所以我们要类型转换。
        * 为了提升代码重用和封装的思想，这里我们可以将这方法封装在工具类中！
        * public class Parse {
            public static Integer StringParseInteger(String s,Integer defaultValue){
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return defaultValue;
            }
        }
        *
        * */

//        封装好工具类后，我们要给其传一个默认值，这里最好是0，因为数据库里的索引是从1开始的
        Integer i = Parse.StringParseInteger(id, 0);

//        调用删除图书方法
        bookService.delBook(i);
        
//        请求重定向回原页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");

    }
```

- 直到这，当我们点击删除，浏览器会弹出提示框，提示是否删除哪本书。

### 修改图书

- 在修改图书之前，首先我们要考虑一个问题，就是是否要有新页面？不用新页面自然可以大大增加效率。

- 在这里自然不用考虑新页面，我们直接可以用添加的页面便可以了。

- 所以我们要把添加页面的按钮添加，改为提交，比较直观。

- 那么，首先，我们点击修改图书要链接到添加图书页面，并且要将原有的图书信息放进去，所以这里就是我们第一个难度，我们首先得将数据传到Servlet，然后再通过Servlet将数据获取，请求转发到添加图书页面。

  所以我们要在Servlet中另写一个方法来获取图书。

  ```java
      protected void getBook(HttpServletRequest request,HttpServletResponse response) throws Exception {
  //        获取参数
          Integer id = Parse.StringParseInteger(request.getParameter("id"), 0);
  //        查询相应的图书
          book book = bookService.queryBook(id);
  //        将图书保存到request域当中
          request.setAttribute("book",book);
  //        请求转发，去到/manager/book_edit.jsp
          request.getRequestDispatcher("/manager/book_edit.jsp").forward(request,response);
      }
  ```

  写完这个以后，我们要在相应jsp页面中，镶嵌相应的数据。如：

  ```jsp
  <td><label for="name">名称:</label></td>
  <td><input type="text" name="name" id="name" value="${requestScope.book.name}"/></td>
  ```

  其他都一样。

- 那么，问题就来了，当我们点击修改的时候，页面过来了，数据也过来了，可是服务器怎么知道我们要修改还是添加？

  我们可以观察，我们是通过添加图书的jsp页面里的隐藏域input标签在Servlet里获取属性的，才知道是add方法，所以我们是否也可以通过这样去获取，让服务器知晓我们到底是添加还是修改？

  答案自然是可以的！

  首先我们要在后台管理首页的jsp中，修改添加和修改的操作路径。

  添加：

  ```jsp
  <td><a href="manager/book_edit.jsp?method=add">添加</a></td>
  ```

  - 既然修改了添加的路径，那么我们之前写过的一个当添加项里为空是拒绝添加就要改回来。

    在Servlet程序中的add方法里。

    ```java
    request.getRequestDispatcher("/manager/book_edit.jsp?method=add").forward(request,response);
    ```

  修改：

  ```jsp
  <td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update">修改</a></td>
  ```

  - 同时，我们也要修改Servlet程序中的getBook方法里的一个路径。

    ```java
    request.getRequestDispatcher("/manager/book_edit.jsp?method=update").forward(request,response);
    ```

  我们加上了这个method属性，就可以直接在添加图书的jsp页面的隐藏域标签input里来获取相应的操作：

  ```jsp
  <input type="hidden" name="action" value="${param.method}"/>
  ```

  那么接下来，我们就可以编写Servlet程序了。

  ```jsp
      protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取参数
          book parameterMap = bean.getParameterMap(request.getParameterMap(), new book());
  //        调用更新方法
          bookService.updateBook(parameterMap);
  //        重定向返回后台管理首页,并展示修改完的图书
          response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
  
      }
  ```

  接下来功能应该全面，但是运行起来问题又来了，明明修改了数据库却无法显示修改后的数据？

  这是为什么？

  原因很简单，我们是设置数据库的id属性为主键，在修改图书的jsp页面里的隐藏域标签input里，name属性只是action，并没有id，或者说整个jsp页面就没有可以获取id的标签，所以我们只能再加上一个隐藏域input标签，name属性等于id，当Servlet这边调用方法的时候才能获取到id值。

  ```jsp
  <input type="hidden" name="id" value="${requestScope.book.id}">
  ```

### 分页导航栏

- 在我们的后台管理首页，数据过多，虽然div盒子可以滑动，但是在实际开发中，滑动很少出现，都是利用分页功能装载更多的数据，这极大地促进了一张网页加载的效率。

- 下面请看分页导航栏的jsp代码

  ```jsp
  <div class="Pages">
      <a href="#">首页</a>
      <a href="#>上一页</a>
      <a href="#">3</a>
      【当前页】
      <a href="#">5</a>    
      <a href="#">下一页</a>
      <a href="#">末页</a>
      共多少页多少条条记录，到第<input value="4" name="pn" id="pn_input"/>页
      <input type="button" value="确定"/>
  </div>
  ```

  我们可以将这个div放在后台管理首页的jsp有table 标签的div标签下，然后编写其样式，让其居于该div标签下。

  ```css
  .Pages {
      text-align: center;
      position: absolute;
      left: 50%;top: 82.5%;
      transform: translate(-50%,-50%);
  }
  ```

- 其次，我们要考虑的是，分页导航栏需要的属性是，每页的数据量，还有每页的数据等等，所以我们要创建一个类，如同book类那般，获取分页的数据等。

  ```java
  package www.hyb.pojo;
  
  /*
  * 这是分页的模型类，为了增强的代码可重用性，可见此类设计为泛型
  * */
  
  import java.util.List;
  
  public class page<T> {
  //    每页最大的数据量
      public static final Integer PAGE_SIZE=10;
  //    当前页码
      private Integer pageNo;
  //    总页数
      private Integer pageTotal;
  //    当前页数据量
      private Integer pageSize=PAGE_SIZE;
  //    总记录数
      private Integer pageTotalCount;
  //    当前页数据
      private List<T> items;
  
      public page() {
      }
  
      public page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
          this.pageNo = pageNo;
          this.pageTotal = pageTotal;
          this.pageSize = pageSize;
          this.pageTotalCount = pageTotalCount;
          this.items = items;
      }
  
      public static Integer getPageSize() {
          return PAGE_SIZE;
      }
  
      public void setPageSize(Integer pageSize) {
          this.pageSize = pageSize;
      }
  
      public Integer getPageTotalCount() {
          return pageTotalCount;
      }
  
      public void setPageTotalCount(Integer pageTotalCount) {
          this.pageTotalCount = pageTotalCount;
      }
  
      public List<T> getItems() {
          return items;
      }
  
      public void setItems(List<T> items) {
          this.items = items;
      }
  
      public Integer getPageNo() {
          return pageNo;
      }
  
      public void setPageNo(Integer pageNo) {
          this.pageNo = pageNo;
      }
  
      public Integer getPageTotal() {
          return pageTotal;
      }
  
      public void setPageTotal(Integer pageTotal) {
          this.pageTotal = pageTotal;
      }
  
      @Override
      public String toString() {
          return "page{" +
                  "pageNo=" + pageNo +
                  ", pageTotal=" + pageTotal +
                  ", pageSize=" + pageSize +
                  ", pageTotalCount=" + pageTotalCount +
                  ", items=" + items +
                  '}';
      }
  }
  ```

- 写完后，我们要考虑的是，每次访问后台管理的首页都会出现该导航栏，所以我们之前的后台首页的链接地址得修改。

  ```jsp
  manager/bookServlet?action=page
  ```

- 然后，我们在Servlet里创建一个方法，就如之前的后台首页方法一般，每次点击后台管理，都会跳转到该方法中。

  ```java
      protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取请求参数pageNo,pageSize
          Integer pageNo = Parse.StringParseInteger(request.getParameter("pageNo"), 1);
          Integer pageSize = Parse.StringParseInteger(request.getParameter("pageSize"), page.PAGE_SIZE);
  //        获取一个page对象
          page<book> page = bookService.page(pageNo, pageSize);
  //        将数据保存到request域当中
          request.setAttribute("page",page);
  //        请求转发
          request.getRequestDispatcher("/manager/manager_book.jsp?action=page").forward(request,response);
      }
  ```

- 上面的方法中，我们可以看到，在获取page对象时，我们是没有page这个方法的，所以，我们按住alt+shift去创建这个方法。

  这个方法在bookService里：

  ```java
  page<book> page(Integer pageNo, Integer pageSize);
  ```

  然后我们得写这个方法的实现。

  ```java
      @Override
      public page<book> page(Integer pageNo, Integer pageSize) {
          page<book> page = new page<>();
  //        设置当前页码
          page.setPageNo(pageNo);
  //        设置当前页最大存储数据量
          page.setPageSize(pageSize);
  //        求总记录数，因为这涉及到数据库，所以方法在Dao层
          Integer count = bookDao.countAll();
  //        设置总记录数
          page.setPageTotalCount(count);
  //        求总页码=总记录数/每页记录数,若是除不尽，要+1
          int countAll=count/pageSize;
          if (count%pageSize>0){
              countAll++;
          }
  //        设置总页码
          page.setPageTotal(countAll);
  //        求当前页数据
          /*
          *求当前页数据的开始索引
          * begin=(当前页码-1)*当前页面数据量
          * */
          int begin=(page.getPageNo()-1)*pageSize;
          List<book> items=bookDao.countPresentDataNum(begin,pageSize);
  //        设置当前页数据
          page.setItems(items);
  
  
          return page;
      }
  ```

- 写到这，我们又看到，我们还需要两个方法，到了最底层是操作数据库的存在，所以又回到了Dao和其实现上。

  ```java
  Integer countAll();
  
  List<book> countPresentDataNum(Integer pageNo,Integer pageSize);
  ```

  ```java
      @Override
      public Integer countAll() {
          String sql="select count(*) from book";
  
          /*
          * 这里我们调用查询某个值的方法，在BaseDao里
          * */
  //        return (Integer) queryOneValue(sql);
          /*
          * 注意，像以上的写法是错误的，因为Object类型不能转换为Integer类型
          * */
          Number number =(Number) queryOneValue(sql);
          return number.intValue();
      }
  
      @Override
      public List<book> countPresentDataNum(Integer begin,Integer pageSize) {
          String sql="select id,name,author,price,sales,stock,img_path from book limit ?,?";
          return queryList(book.class,sql,begin,pageSize);
      }
  ```

- 写到这，主要的实现几乎完成，但是我们得在web层接受服务器发送的数据。

  ```jsp
  <div class="Pages">
      这里加入if标签主要是：
      假如刚好到了首页，我们就不用显示首页的文字说明了。其他道理类似。
      <c:if test="${requestScope.page.pageNo>1}">
          <a href="manager/bookServlet?action=page&pageNo=1">首页</a>
          <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
      </c:if>
  
      <a href="#">3</a>
      【${requestScope.page.pageNo}】
      <a href="#">5</a>
      <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
          <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
          <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
      </c:if>
  
      共${requestScope.page.pageTotal}页${requestScope.page.pageTotalCount}条记录，到第<input value="4" name="pn" id="pn_input"/>页
      <input type="button" value="确定"/>
  </div>
  ```

- 接下来，我们实现页面搜索功能。

  ```jsp
  <label for="Page_input"></label>
  <input value="${requestScope.page.pageNo}" name="pn" id="Page_input"/>页
  <input id="PageThrow" type="button" value="确定"/>
  
  <%--
      下面的是隐藏域，因为我们要在js文件里获取总页数，判断如果输入超出了总页数就不合法
  --%>
  <input id="pageTotal" type="hidden" value="${requestScope.page.pageTotal}">
  ```

  我们要达到的效果是，在input标签里输入一个数字，该数字是存在的页数，点击可以跳转到该页面，并且显示该页是第几页。

  所以我们还剩下一个单击事件，我们可以在相应的js文件中写下单击事件。

  ```js
  $("#PageThrow").click(function () {
      var pageNo=parseInt($("#Page_input").val());
      var pageTotal=parseInt($("#pageTotal").val());
      if (pageNo>0&&pageNo<=pageTotal){
          window.location.href="manager/bookServlet?action=page&pageNo="+pageNo;
      }else{
          confirm("你输入的页数不合法！");
      }
  });
  ```

  注意，这里得到的数据是字符类型的，所以你要判断必须转换成数字型。

### 单页点击

- 在页面导航栏，我们看见了可以进行单页点击，这里我们修改以下，要求单击点击栏有如何功能。
- 提供五个单页，要求点击所到的页面进行【】标注。

```jsp
<c:choose>
    <c:when test="${requestScope.page.pageTotal<=5}">
        <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
            <c:if test="${i==requestScope.page.pageNo}">
                【${i}】
            </c:if>
            <c:if test="${i!=requestScope.page.pageNo}">
                <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>
    </c:when>
    <c:when test="${requestScope.page.pageTotal>5}">
        <c:choose>
            
            <c:when test="${requestScope.page.pageNo<=3}">
                <c:forEach begin="1" end="5" var="i">
                    <c:if test="${i==requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i!=requestScope.page.pageNo}">
                        <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:when>
            
            <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                    <c:if test="${i==requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i!=requestScope.page.pageNo}">
                        <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                    <c:if test="${i==requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i!=requestScope.page.pageNo}">
                        <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>
```

- 看见每个条件里都有forEach，我们可以对这个choose进行优化。

```jsp
<%--页码输出的开始--%>
<c:choose>
    <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
    <c:when test="${requestScope.page.pageTotal <= 5}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotal}"/>
    </c:when>
    <%--情况2：总页码大于5的情况--%>
    <c:when test="${requestScope.page.pageTotal>5}">
        <c:choose>
            <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
            <c:when test="${requestScope.page.pageNo<=3}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="5"/>
            </c:when>
            <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>
<c:forEach begin="${begin}" end="${end}" var="i">
    <c:if test="${requestScope.page.pageNo == i}">
        【${i}】
    </c:if>
    <c:if test="${requestScope.page.pageNo != i}">
        <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
    </c:if>
</c:forEach>
```

### 前台展示

- 前台展示要求展示出所有保存到数据库里的信息，也可进行分页处理，要求提供区间搜索，单页点击，还有标题导航栏。

- 在前台展示之前，我们做一个小小的优化，在前面，我们将首页包含的登录功能放在项目固有的index.jsp文件中，让其服务器开动便可以立马访问该地址，现在，我们将这里面的代码赋值到另一个jsp文件中，名为Login，这里表示登录，该文件放在static包下，而我们剩余的空白index jsp文件只保留文件头，其他标签删除，我们要动态加入一个标签。

  ```jsp
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <jsp:forward page="/client/clientBookServlet?action=page"></jsp:forward>
  ```

  被动态包含的路径，正是我们前台展示的Servlet路径，我们依据创建一个Servlet程序，然后位置web.xml文件，自定义路径要和这里的相同。

  然后我们编写该Servlet

  ```java
  package www.hyb.web;
  
  
  import www.hyb.pojo.book;
  import www.hyb.pojo.page;
  import www.hyb.service.BookService;
  
  import www.hyb.service.impl.BookServiceImpl;
  import www.hyb.untils.Parse;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class ClientBookServlet extends BaseServlet {
  
      private final BookService bookService=new BookServiceImpl();
  
      protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          //        获取请求参数pageNo,pageSize
          Integer pageNo = Parse.StringParseInteger(request.getParameter("pageNo"), 1);
          Integer pageSize = Parse.StringParseInteger(request.getParameter("pageSize"), page.PAGE_SIZE);
  //        获取一个page对象
          page<book> page = bookService.page(pageNo, pageSize);
  //        将数据保存到request域当中
          request.setAttribute("page",page);
  //        请求转发
          request.getRequestDispatcher("/client/index.jsp?action=page").forward(request,response);
  
  
      }
  }
  ```

  走到这里，我们便看到请求转发到了一个陌生的路径，该路径不难猜出，便是我们前台展示的路径。

  所以，以上的思路总结便是：让浏览器首次进入index jsp文件，包含一个Servlet路径，然后利用该Servlet请求转发到真正的前台展示路径。这样似乎感觉多走了一层？

  其实不然！

  这里我们先不解释，先编写出前台展示的jsp再说。

- 下面是前台展示的jsp文件。

  ```jsp
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
          <%@include file="/common/manager_menu.jsp"%>
      </div>
      <div class="MainBox" >
          <div class="indexBox" >
              <label for="FirstPrice" ></label>
              价格:<input type="text" name="FirstPrice" id="FirstPrice">
              <label for="EndPrice"></label>
              -<input type="text" name="EndPrice" id="EndPrice">
          </div>
          <p class="count">您购物车有${requestScope.page.pageTotalCount}件商品</p>
          <p class="count">您刚刚将什么加入到了购物车中</p>
          <form  action="" method="post">
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
                                  <td colspan="2"><input type="submit" id="addCart" value="添加至购物车"/></td>
                              </tr>
                          </table>
  
              </c:forEach>
          </form>
      </div>
      <div class="Pages">
          <c:if test="${requestScope.page.pageNo>1}">
              <a href="client/clientBookServlet?action=page&pageNo=1">首页</a>
              <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
          </c:if>
  
          <%--页码输出的开始--%>
          <c:choose>
              <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
              <c:when test="${requestScope.page.pageTotal <= 5}">
                  <c:set var="begin" value="1"/>
                  <c:set var="end" value="${requestScope.page.pageTotal}"/>
              </c:when>
              <%--情况2：总页码大于5的情况--%>
              <c:when test="${requestScope.page.pageTotal>5}">
                  <c:choose>
                      <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                      <c:when test="${requestScope.page.pageNo<=3}">
                          <c:set var="begin" value="1"/>
                          <c:set var="end" value="5"/>
                      </c:when>
                      <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                      <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                          <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                          <c:set var="end" value="${requestScope.page.pageTotal}"/>
                      </c:when>
                      <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                      <c:otherwise>
                          <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                          <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                      </c:otherwise>
                  </c:choose>
              </c:when>
          </c:choose>
          <c:forEach begin="${begin}" end="${end}" var="i">
              <c:if test="${requestScope.page.pageNo == i}">
                  【${i}】
              </c:if>
              <c:if test="${requestScope.page.pageNo != i}">
                  <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
              </c:if>
          </c:forEach>
  
  
          <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
              <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
              <a href="client/clientBookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
          </c:if>
  
          共${requestScope.page.pageTotal}页${requestScope.page.pageTotalCount}条记录，到第
          <label for="Page_input"></label>
          <input value="${requestScope.page.pageNo}" name="pn" id="Page_input"/>页
          <input id="PageThrow" type="button" value="确定"/>
  
          <%--
              下面的是隐藏域，因为我们要在js文件里获取总页数，判断如果输入超出了总页数就不合法
          --%>
          <input id="pageTotal" type="hidden" value="${requestScope.page.pageTotal}">
      </div>
      <%@include file="/common/foot.jsp"%>
  </body>
  </html>
  ```

  撰写css文件

  ```css
  body{
      background-color: white;
  }
  
  .header,.count,.indexBox{
      text-align: center;
  }
  
  .MainBox{
      border: solid black;
      width: 60%;
      height: 60%;
      position: absolute;
      left: 50%;top: 50%;
      transform: translate(-50%,-50%);
      overflow: auto;
  }
  .Pages {
      text-align: center;
      position: absolute;
      left: 50%;top: 82.5%;
      transform: translate(-50%,-50%);
  }
  
  .list{
      float: left;
  }
  
  
  
  .img img{
      width: 210px;
      height: 200px;
      float: left;
  }
  ```

  然后撰写单页点击的js文件

  ```js
  $(function () {
      $("#PageThrow").click(function () {
          var pageNo=parseInt($("#Page_input").val());
          var pageTotal=parseInt($("#pageTotal").val());
          if (pageNo>0&&pageNo<=pageTotal){
              window.location.href="client/clientBookServlet?action=page&pageNo="+pageNo;
          }else{
              confirm("你输入的页数不合法！");
          }
      });
  });
  ```

- 写到这，你便可以正常的浏览了，开启服务器，马上便是前台展示页，没有到登录页，这里先不解析为什么，因为只是为了演示才这样的。其次，我们分析为什么不直接跳转到前台展示页，还要进行请求转发，我们正常浏览便会发现一个问题，如果我们用现在的方法，地址栏会发生变化，并不直接将Servlet的地址展示出来了，而是一个本机的地址，这就极大的增强了地址的安全性。

### 分页条抽取

- 我们对比一下后台首页和前台首页的分页条就会发现，只有url地址不同，所以我们可以对其进行抽取。

- 但url始终是不同，但这并不影响，我们可以在page类下，设置一个元素名为url，然后提供其相关方法。

- 之后在对应的bookServlet和clientBookServlet中利用其set方法设置url，然后保存到request域中，这样我们在分页条的jsp当中就可以直接获取。

  ```jsp
  <div class="Pages">
      <c:if test="${requestScope.page.pageNo>1}">
          <a href="${requestScope.page.url}&pageNo=1">首页</a>
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
      </c:if>
  
      <%--页码输出的开始--%>
      <c:choose>
          <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
          <c:when test="${requestScope.page.pageTotal <= 5}">
              <c:set var="begin" value="1"/>
              <c:set var="end" value="${requestScope.page.pageTotal}"/>
          </c:when>
          <%--情况2：总页码大于5的情况--%>
          <c:when test="${requestScope.page.pageTotal>5}">
              <c:choose>
                  <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                  <c:when test="${requestScope.page.pageNo<=3}">
                      <c:set var="begin" value="1"/>
                      <c:set var="end" value="5"/>
                  </c:when>
                  <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                  <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                      <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                      <c:set var="end" value="${requestScope.page.pageTotal}"/>
                  </c:when>
                  <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                  <c:otherwise>
                      <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                      <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                  </c:otherwise>
              </c:choose>
          </c:when>
      </c:choose>
      <c:forEach begin="${begin}" end="${end}" var="i">
          <c:if test="${requestScope.page.pageNo == i}">
              【${i}】
          </c:if>
          <c:if test="${requestScope.page.pageNo != i}">
              <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
          </c:if>
      </c:forEach>
  
  
      <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
          <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
      </c:if>
  
      共${requestScope.page.pageTotal}页${requestScope.page.pageTotalCount}条记录，到第
      <label for="Page_input"></label>
      <input value="${requestScope.page.pageNo}" name="pn" id="Page_input"/>页
      <input id="PageThrow" type="button" value="确定"/>
  
      <%--
          下面的是隐藏域，因为我们要在js文件里获取总页数，判断如果输入超出了总页数就不合法
      --%>
      <input id="pageTotal" type="hidden" value="${requestScope.page.pageTotal}">
  </div>
  ```

- 这样我们就可以将其包裹在common包下，撰写一个公有文件，最后分别在各自jsp文件中包含进来就可以了。

- 这样极大地方便了代码的维护。

### 价格区间搜索

- 前面初步拟定的代码中，价格区间搜索的代码得改一改。

  ```jsp
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
  ```

  - 首先，提供表单，用来提交事件的发送地址。其次，便是隐藏域，获取action=PriceSearch，找到在Servlet中相应的方法。

    之后便是${param.FirstPrice}等用来表示价格回显。

- 改完代码后，创造相应的Servlet方法，名字为action值，与之前的page分页差不多一样。

  ```java
      protected void PriceSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取请求参数pageNo,pageSize
          Integer pageNo = Parse.StringParseInteger(request.getParameter("pageNo"), 1);
          Integer pageSize = Parse.StringParseInteger(request.getParameter("pageSize"), page.PAGE_SIZE);
          Integer min = Parse.StringParseInteger(request.getParameter("FirstPrice"), 0);
          Integer max = Parse.StringParseInteger(request.getParameter("EndPrice"), Integer.MAX_VALUE);
  
  //        获取一个page对象
          page<book> page = bookService.PriceSearch(pageNo, pageSize,min,max);
  //        将数据保存到request域当中
          request.setAttribute("page",page);
  
          String url="client/clientBookServlet?action=PriceSearch";
  
  //        设置请求地址并保存
          
          /*
          * 使得点击下一页的时候会跳转到回显的页面
          * */
          
          if (request.getParameter("FirstPrice")!=null){
              url+="&FirstPrice="+min;
          }
          if (request.getParameter("EndPrice")!=null){
              url+="&EndPrice="+max;
          }
          
          
          page.setUrl(url);
          request.setAttribute("url",page.getUrl());
  
  //        请求转发
          request.getRequestDispatcher("/client/index.jsp?action=PriceSearch").forward(request,response);
      }
  ```

  如page那里分页一样，我们实现相应的方法，在这之前，必须得在其接口层进行函数声明。

  下面只讲实现：

  BookServiceImpl层

  ```java
      @Override
      public page<book> PriceSearch(Integer pageNo, Integer pageSize, Integer min, Integer max) {
          page<book> page = new page<>();
  //        设置当前页码
          page.setPageNo(pageNo);
  //        设置当前页最大存储数据量
          page.setPageSize(pageSize);
  //        求总记录数，因为这涉及到数据库，所以方法在Dao层
          Integer count = bookDao.countAllByPrice(min,max);
  //        设置总记录数
          page.setPageTotalCount(count);
  //        求总页码=总记录数/每页记录数,若是除不尽，要+1
          int countAll=count/pageSize;
          if (count%pageSize>0){
              countAll++;
          }
  //        设置总页码
          page.setPageTotal(countAll);
  //        求当前页数据
          /*
           *求当前页数据的开始索引
           * begin=(当前页码-1)*当前页面数据量
           * */
          int begin=(page.getPageNo()-1)*pageSize;
          List<book> items=bookDao.countPresentDataNumByPrice(begin,pageSize,min,max);
  //        设置当前页数据
          page.setItems(items);
  
          return page;
      }
  ```

  然后继续实现其中的方法，老样子，接口必须先声明。

  下面讲话实现。

  BookDaoImpl层

  ```java
  @Override
  public List<book> countPresentDataNumByPrice(int begin, Integer pageSize, Integer min, Integer max) {
      String sql="select id,name,author,price,sales,stock,img_path from book where price > ? and price < ? order by price limit ?,?";
      return queryList(book.class,sql,min,max,begin,pageSize);
  }
  
  @Override
  public Integer countAllByPrice(Integer min, Integer max) {
      String sql="select count(*) from book where price > ? and price < ?";
      Number number =(Number) queryOneValue(sql,min,max);
      return number.intValue();
  }
  ```

- 写到这，功能基本都实现了，但是还有一个分页搜索我们没写，所以，先撰写其js文件，然后引入。

  ```js
  $(function () {
      $("#PageThrow").click(function () {
          var pageNo=parseInt($("#Page_input").val());
          var pageTotal=parseInt($("#pageTotal").val());
          if (pageNo>0&&pageNo<=pageTotal){
              window.location.href="client/clientBookServlet?action=PriceSearch&FirstPrice="+parseInt($("#FirstPrice").val())+"&EndPrice="+parseInt($("#EndPrice").val())+"&pageNo="+pageNo;
          }else{
              confirm("你输入的页数不合法！");
          }
      });
  });
  ```

## Session 和 Cookie

### Session

#### 登录提醒

- 可以做登录提醒。

- 截止目前，我们网站的首页是前台浏览页，那么我们可以加上登录和注册的功能。

  ```
  <div class="loginOrRegister">
      <c:if test="${empty sessionScope.username}">
          <a href="static/Login.jsp">登录</a>
          |
          <a href="static/Register.jsp">注册</a>
      </c:if>
      <c:if test="${not empty sessionScope.username}">
          <a href="client/mainIndex.jsp">谁的主页</a>
      </c:if>
  </div>
  ```

- 谁的主页中，这个谁，我们利用Session获取，但首先我们得回到登录的Servlet里，当我们登录成功时候，要加入下面这句话。

  ```java
  request.getSession().setAttribute("username",username);
  ```

- 那么谁这个字，便可以用${sessionScope.username}代替了，这样一旦我们登录成功，就会提醒我们可以点击谁的主页，也就是是谁的账号。

#### 注销登录

- 在登录成功页面有注销功能，我们找到注销的jsp页面，修改如下。

  ```jsp
  
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <div>
      <a href="#">我的订单</a><br/>
      <a href="#">返回</a><br/>
      <a id="loginDown" href="UserServlet?action=loginDown">注销账号</a><br/>
  </div>
  ```

- 所以，我们得在用户的Servlet中创建一个loginDown方法，如下：

  ```java
      /*
      * 实现注销功能
      * */
      protected void loginDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  //        消除session
          request.getSession().invalidate();
  //        重定向到首页
          response.sendRedirect(request.getContextPath());
      }
  ```

- 其次，我们还可以为注销功能提供提醒按钮。

  ```js
  $(function () {
      $("#loginDown").click(function () {
          return confirm("请确定要退出登录吗?");
      });
  });
  ```

- 最后，在登录成功的页面链入该js文件即可。

## 验证码

- 前面提到过表单重复提交，可是表单重复提交可不止这些。
- 一旦用户网络延迟，多次点击，也会重复提交，不仅如此，若是用户登录成功了，回退登录网址，也会造成表单多次提交问题。
- 所以这就是要采用验证码登录的原因。

### 显示验证码

- 首先要导入kaptcha-2.3.2.jar 包，并且让其有效，然后我们配置一个web.xml 如下。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
          <servlet-name>KaptchaServlet</servlet-name>
          <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>
      </servlet>
  
      <servlet-mapping>
          <servlet-name>KaptchaServlet</servlet-name>
          <url-pattern>/kaptcha.jpg</url-pattern>
      </servlet-mapping>
  </web-app>
  ```

### 使用验证码

- 找到注册的Servlet，将固定的验证码改变就可以了。

```java
        /*
        * 获取验证码
        * */

//        从session中获取
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        马上删除，防止第二次使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);



//        验证码是否错误
        if (token!=null&&token.equalsIgnoreCase(checkcode)){
        
        …………
```

### 看不清，下一张

- 首先我们为其表写一个标签。

  ```jsp
  <a id="img_alert"><i>看不清？下一张</i></a>
  ```

- 然后我们在注册里为其编写js文件，这里我们首先要知道，存放验证码的标签为：

  ```jsp
  <img src="kaptcha.jpg" id="img_check" alt="静态验证码为：abcd">
  ```

- 那么js文件就好写了。

  ```js
  $("#img_alert").click(function () {
      $("#img_check").attr("src","kaptcha.jpg?d="+new Date());
  });
  ```

- new Date() 是为了每次提交的验证码路径格都不一样，防止浏览器缓存而导致刷新不了。当然你也可以用其他方式表现不同。

## 购物车

### 创建两个类

```java
package www.hyb.pojo;

import java.math.BigDecimal;

public class cartItem {
    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public cartItem() {
    }

    public cartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "cartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
```

```java
package www.hyb.pojo;

/*
* 购物车对象,也就是每一种商品，前面是商品固有的一些属性
* */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,cartItem> map=new LinkedHashMap<>();

    /*
    * 添加商品项
    * */

    public void addItem(cartItem cartItem){
        www.hyb.pojo.cartItem item = map.get(cartItem.getId());
        if (item==null){
//            之前没添加过
            map.put(cartItem.getId(),cartItem);
        }else{
//            之前添加过，数量加1，总价增加
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /*
    * 删除商品项
    * */

    public void deleteItem(Integer id){
        map.remove(id);
    }

    /*
    * 清空购物车
    * */

    public void clearItem(){
        map.clear();
    }

    /*
    * 修改商品数量
    * */

    public void updateItemCount(Integer id,Integer count){
        cartItem item = map.get(id);
        if (item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,cartItem> entry:map.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,cartItem> entry:map.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, cartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, cartItem> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", map=" + map +
                '}';
    }
}
```

### Servlet

- 在创建Servlet之前，我们先得在有加入购物车的jsp页面中设置以下标签。

  ```
  <form  action="cartServlet" method="post">
      <input type="hidden" name="action" value="addItem">
      <c:forEach items="${requestScope.page.items}" var="book">
          <input type="hidden" name="bookId" id="bookId" value="${book.id}">
  ```

  - 一个是form标签的隐藏域，表示action属性为addItem值，另一个为c:forEach的隐藏域，表示每次遍历出的书的id。

- 创建一个Servlet 为其写好web.xml

  ```java
  package www.hyb.web;
  
  import www.hyb.pojo.book;
  import www.hyb.pojo.cart;
  import www.hyb.pojo.cartItem;
  import www.hyb.service.BookService;
  import www.hyb.service.impl.BookServiceImpl;
  import www.hyb.untils.Parse;
  import www.hyb.untils.bean;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  public class CartServlet extends BaseServlet {
  
      private final BookService bookService=new BookServiceImpl();
  
      protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Integer bookId = Parse.StringParseInteger(request.getParameter("bookId"), 0);
          book book = bookService.queryBook(bookId);
          cartItem cartItem = new cartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
  
          /*
          * 不能直接new一个购物车，因为如果是同样的商品，就造成了数量不能相加
          * 所以要利用session来保存
          * */
  //        cart cart = new cart();
          cart cart = (cart) request.getSession().getAttribute("cart");
          if (cart==null){
              cart = new cart();
              request.getSession().setAttribute("cart",cart);
          }
          cart.addItem(cartItem);
  //        重定向回原来地址
          response.sendRedirect(request.getHeader("Referer"));
      }
  }
  ```

- web.xml 简单，自行编写。

### jsp模块

本页：

```jsp
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
        <a href="#">清空购物车</a>
        <a href="#">结账</a>
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
                        <td>${entry.value.count}</td>
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

```

有添加购物车功能的页面：

```jsp
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
        <p class="count">
            您的购物车共有${sessionScope.cart.totalCount}商品，您刚刚将${sessionScope.lastName}加入到了购物中！
        </p>
        <c:forEach items="${requestScope.page.items}" var="book">
            <form action="cartServlet" method="post">
                <input type="hidden" name="action" value="addItem">
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
                                所以用name属性，传给form标签，并且每次获取新的id值
                            --%>
                            <input type="hidden" class="bookId" name="bookId" value="${book.id}">
                            <input type="submit" class="addCart" value="添加至购物车"/>
                        </td>
                    </tr>
                </table>
            </form>

        </c:forEach>
<%--        </form>--%>
    </div>
    <%@ include file="/common/PagingNavigation.jsp"%>
    <%@include file="/common/foot.jsp"%>
</body>
</html>
```

### 删除Servlet

```java
protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer id = Parse.StringParseInteger(request.getParameter("id"), 0);
    cart cart=(cart) request.getSession().getAttribute("cart");
    if (cart!=null){
        cart.deleteItem(id);
    }
    response.sendRedirect(request.getHeader("Referer"));
}
```

### 提醒js

```js
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
})
```

### 清空购物车

```jsp
<a class="clearCart" href="cartServlet?action=clear">清空购物车</a>
```

```js
$("a.clearCart").click(function () {
    /*
    * 这里有个this对象，表示当前所在的标签元素。
    * 通过一次parent可获取上级标签，再parent，便是上一个标签的上一个
    * 这里的意思是，通过标签a找到标签td，再找到tr，通过tr找到其第一个td
    * 再获取第一个td就能获取其名字
    * */
    return confirm("你确定要清空购物车吗？")
```

### 修改数量

```jsp
td>
    <button class="add" value="add">+</button>
    <label>
        <c:if test="${entry.value.count<0}">
            <input class="updateNum" style="width: 80px;" type="text" value="0">
        </c:if>
        <c:if test="${entry.value.count>=0}">
            <input class="updateNum" style="width: 80px;" type="text" value="${entry.value.count}">
        </c:if>
        <input type="hidden" class="bookId" value="${entry.value.id}">
    </label>
    <button class="delete" value="delete">-</button>
</td>
```

```js
$(".updateNum").change(function () {
    let bookId= $(".bookId").val();
    let count = $(".updateNum").val();
    if (confirm("你确定要将此数量修改为"+count)){
        window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
    }else{
        this.value=this.defaultValue;
    }
});
$(".add").click(function () {
    let bookId= parseInt($(".bookId").val());
    let count = parseInt($(".updateNum").val());
    count=count+1
    window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
});
$(".delete").click(function () {
    let bookId= parseInt($(".bookId").val());
    let count = parseInt($(".updateNum").val());
    count=count-1;
    window.location.href="cartServlet?action=updateItem&id="+bookId+"&count="+count;
});
```

- 上述是一个错误版本，别以为是对的，等你加多几个数据，会发现点击其他加号或者减号都是操作的第一条数据，小编可能觉得是因为这个button标签和input标签加载的问题，所以就直接将获取的值放入每个标签里。

```jsp

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

```

js文件：

```js
$(function () {
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
```

## 订单模块

### 订单表和订单项表

```sql
use hyb
create table hyb_order(
	orderId varchar(50) primary key,
	createTime datetime,
	price decimal(11,2),
	satatus int,
	userId int,
	foreign key(userId) references user(id)
)

create table hyb_order_item(
	id int primary key auto_increment,
	name varchar(100),
	count int,
	price decimal(11,2),
	total_price decimal(11,2),
	orderId varchar(50),
	foreign key(orderId) references hyb_order(orderId)
)
```

### 订单javabean和订单项javabean

```java
package www.hyb.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class order {
//    orderId varchar(50) primary key,
//    createTime datetime,
//    price decimal(11,2),
//    satatus int,
//    userId int,
//    foreign key(userId) references user(id)
    private String  orderId;
    private Date createTime;
    private BigDecimal price;
    private Integer status=0;
    private Integer userId;

    public order() {
    }

    public order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
```

```java
package www.hyb.pojo;

import java.math.BigDecimal;

public class orderItem {
//    id int primary key auto_increment,
//    name varchar(100),
//    count int,
//    price decimal(11,2),
//    total_price decimal(11,2),
//    orderId varchar(50),
//    foreign key(orderId) references hyb_order(orderId)

    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String orderId;

    public orderItem() {
    }

    public orderItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "orderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
```

### Dao层操作数据库

```java
package www.hyb.dao;


import www.hyb.pojo.order;

public interface orderDao {
    public int saveOrder(order order);
}
```

```java
package www.hyb.dao;

import www.hyb.pojo.orderItem;

public interface orderItemDao {
    public int saveOrderItem(orderItem orderItem);
}
```

### DaoImpl 层实现Dao层

```java
package www.hyb.dao.impl;

import www.hyb.dao.orderDao;
import www.hyb.pojo.order;

import java.math.BigDecimal;

public class orderDaoImpl extends BaseDao implements orderDao {
    @Override
    public int saveOrder(order order) {
        String sql="insert into hyb_order(orderId,createTime,price,satatus,userId) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
```

```java
package www.hyb.dao.impl;


import www.hyb.dao.orderItemDao;
import www.hyb.pojo.orderItem;

public class orderItemDaoImpl extends BaseDao implements orderItemDao {
    @Override
    public int saveOrderItem(orderItem orderItem) {
        String sql="insert into hyb_order_item(name,count,price,total_price,orderId) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
```

### 阶段测试

```java
package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.orderDaoImpl;
import www.hyb.dao.orderDao;
import www.hyb.pojo.order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class orderDaoImplTest {

    @Test
    public void saveOrder() {
        orderDao orderDao=new orderDaoImpl();
        orderDao.saveOrder(new order("111111",new Date(),new BigDecimal(100),0,1));
    }
}
```

```java
package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.orderItemDaoImpl;
import www.hyb.dao.orderItemDao;
import www.hyb.pojo.orderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class orderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        orderItemDao orderItemDao=new orderItemDaoImpl();
        orderItemDao.saveOrderItem(new orderItem(null,"java",1,new BigDecimal(101),new BigDecimal(100),"111111"));
    }
}
```

### Service层

```java
package www.hyb.service;

import www.hyb.pojo.cart;

public interface orderService {
    public String createOrder(cart cart, Integer userId);
}
```

```java
package www.hyb.service.impl;

import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.dao.impl.orderDaoImpl;
import www.hyb.dao.impl.orderItemDaoImpl;
import www.hyb.dao.orderDao;
import www.hyb.dao.orderItemDao;
import www.hyb.pojo.*;
import www.hyb.service.orderService;

import java.util.Date;
import java.util.Map;

public class orderServiceImpl implements orderService {

    private final orderDao orderDao=new orderDaoImpl();
    private final orderItemDao orderItemDao=new orderItemDaoImpl();
    private final BookDao bookDao=new BookDaoImpl();

    /*
    * 创建订单
    * */
    @Override
    public String createOrder(cart cart, Integer userId) {
//        创建订单号，时间戳+用户Id
        String orderId= System.currentTimeMillis()+""+userId;
//        创建一个订单对象
        order order = new order(orderId,new Date(),cart.getTotalPrice(),0,userId);
//        保存订单
        orderDao.saveOrder(order);
//        从购物车里遍历数据到数据库
        for (Map.Entry<Integer, cartItem>entry:cart.getMap().entrySet()) {
//            获取每一个购物车中的商品项
            cartItem value = entry.getValue();
//            转换为每一个订单项
            orderItem orderItem = new orderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),orderId);
//            保存数据项到数据库
            orderItemDao.saveOrderItem(orderItem);
//            每生成订单一次，销量和库存对应变化
            book book = bookDao.queryBook(value.getId());
            book.setSales(book.getSales()+value.getCount());
            book.setStock(book.getStock()-value.getCount());
            bookDao.updateBook(book);
        }
//        清空购物车
        cart.clearItem();
        return orderId;
    }
}

```

### Servlet

```java
package www.hyb.web;

import www.hyb.pojo.cart;
import www.hyb.pojo.user;
import www.hyb.service.impl.orderServiceImpl;
import www.hyb.service.orderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private final orderService orderService=new orderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取一个购物车
        cart cart = (cart) request.getSession().getAttribute("cart");
//        获取登录id
        user user =(user) request.getSession().getAttribute("user");
        if (user==null){
            request.getRequestDispatcher("/static/Login.jsp").forward(request,response);
            return;
        }
        Integer id = user.getId();
//        生成订单
        String orderId = orderService.createOrder(cart, id);
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/client/order.jsp");

//        request.setAttribute("orderId",orderId);
//        request.getRequestDispatcher("/client/order.jsp").forward(request,response);
    }
}
```

注意：我们这里获取的id是空的，因为前面我们的数据库虽然的用户id虽然是自增且自动生成的，但是每次我们new一个user对象的时候并没有把id放进去，所以不会得到一个id。

这里我们得在最开始的dao层编写面向数据表的查询某个id值的代码：

```java
public Integer queryId(user u);
```

```java
@Override
public Integer queryId(user u) {
    String sql="select id from user where username=?";
    return (Integer) queryOneValue(sql,u.getUsername());
}
```

编写完dao，为了思想与之前的统一，所以要编写service层

```java
//    获取id
    public Integer getUserId(user u);
```

```java
@Override
public Integer getUserId(user u) {
    return userDao.queryId(u);
}
```

编写完service层，就是servlet层了，因为我们之前学习session的时候是将user的对象在登录时存在了session域中，所以我们就找到登录的servlet方法。

找到后，我们可以看到，再创造user对象的时候id是空的，这也就导致了session域中的id也是空的，所以我们要在new一个user后重新给其id赋值。

```java
Integer userId = userService.getUserId(user);
user.setId(userId);
```

这个事情必须在放进session域之前完成。

在这之后，在jsp页面便可通过session域获取完整的订单号了。

## Filter

### 后台管理

- 这里我们利用Filter过滤器，防止未登录的时候访问后台。

- 首先得在src下创建一个Filter包，然后在里面创建一个filter过滤器，之后配置web.xml文件。

  ```xml
   <filter>
       <filter-name>ManagerFilter</filter-name>
       <filter-class>www.hyb.filter.ManagerFilter</filter-class>
   </filter>
  <filter-mapping>
      <filter-name>ManagerFilter</filter-name>
      <url-pattern>/manager/*</url-pattern>
      <url-pattern>/manager/bookServlet</url-pattern>
  </filter-mapping>
  ```

  这里配置路径有些不同，多出了一个，其中一个是正常的，表示后台所有页面，后一个也没有毛病，因为我们在写后台的jsp代码的时候，提交的路径都是关于Servlet的路径，若是有人在地址栏写上Servlet的路径，也可以访问到后台，所以Servlet路径也有过滤。

- 下面是过滤器的内容：

  ```java
  package www.hyb.filter;
  
  import javax.servlet.*;
  import javax.servlet.http.HttpServletRequest;
  import java.io.IOException;
  
  public class ManagerFilter implements Filter {
      public void destroy() {
      }
  
      public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
          HttpServletRequest httpServletRequest= (HttpServletRequest) req;
          Object user = httpServletRequest.getSession().getAttribute("user");
          if (user==null){
              req.getRequestDispatcher("/static/Login.jsp").forward(req,resp);
          }else {
              chain.doFilter(req,resp);
          }
      }
  
      public void init(FilterConfig config) throws ServletException {
  
      }
  
  }
  ```

## ThreadLocal

- 使用ThreadLocal 让工程统一获取一个数据库连接，并且使结账功能变为事务，防止结账功能出错时，数据库的数据还会保存。

### 改变连接

- 首先要所有都获取到一个连接，就得来到untils包下的获取连接的方法中更改成以下。

  ```java
      private static final ThreadLocal<Connection> connection=new ThreadLocal<>();
  
  //    获取数据库连接池的连接
      public static Connection connect(){
          Connection connect= JdbcUtils.connection.get();
          if (connect==null){
              try {
                  connect=source.getConnection();
                  connection.set(connect);
  //                设置手动管理事务
                  connect.setAutoCommit(false);
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
          return connect;
      }
  ```

- 然后我们关闭连接分两类，一个是提交事务时一并关闭，一个是回滚事务时一并关闭，

  ```java
  public static void commitClose(){
      Connection connect= JdbcUtils.connection.get();
      if (connect!=null){
          try {
              connect.commit();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }finally {
              try {
                  connect.close();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
      }
      connection.remove();
  }
  
  public static void rollbackClose(){
      Connection connect= JdbcUtils.connection.get();
      if (connect!=null){
          try {
              connect.rollback();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }finally {
              try {
                  connect.close();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }
          }
      }
      connection.remove();
  }
  ```

### BaseDao

- 改变连接后，并且提交和回滚事务都有各自的关闭方法，我么在BaseDao里就不必要关闭连接了，而且，为了方便异常的处理，我们要在提交事务或者回滚事务的时候处理异常，所以要在BaseDao里的每个方法后都得抛出异常。

  这里只举一个例子。

  ```java
  public int update(String sql,Object ... arg){
      Connection connect = JdbcUtils.connect();
      try {
           return queryRunner.update(connect,sql,arg);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
          throw new RuntimeException(throwables);
      }
  }
  ```

  这里的异常也不用try catch 直接自动抛出也行，但要改很多代码。

### 处理订单异常

- 改变完毕后，我们在创建订单的时候就可以额try catch 了。

  ```java
  String orderId = null;
  try {
      orderId = orderService.createOrder(cart, id);
      JdbcUtils.commitClose();
  } catch (Exception e) {
      JdbcUtils.rollbackClose();
      e.printStackTrace();
  }
  ```

- 这个文件OrderServlet中。

- 这样过后，当我们创建订单出现了错误，结账功能也就不会出错了。

## 升级处理

- 在大的项目工程中，不光有结账这一个异常，很多功能都需要事务的操作，但是我们不可能所有的功能都像处理订单异常那样写，不便于维护。

- 这里我们便可以进行升级处理，可以用一个Filter过滤器让整个web工程都处于事务当中。

- 这里我们创建一个Filter包，然后利用其doFilter进行try catch

  ```java
  package www.hyb.filter;
  
  import www.hyb.untils.JdbcUtils;
  
  import javax.servlet.*;
  import java.io.IOException;
  
  public class TransactionFilter implements Filter {
      public void destroy() {
      }
  
      public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
          try {
              chain.doFilter(req, resp);
              JdbcUtils.commitClose();
          } catch (IOException | ServletException e) {
              JdbcUtils.rollbackClose();
              e.printStackTrace();
          }
      }
  
      public void init(FilterConfig config) throws ServletException {
  
      }
  
  }
  ```

- 之后我们配置web.xml使其遍布整个工程的所有资源文件。

  ```xml
      <filter>
          <filter-name>TransactionFilter</filter-name>
          <filter-class>www.hyb.filter.TransactionFilter</filter-class>
      </filter>
      <filter-mapping>
          <filter-name>TransactionFilter</filter-name>
  <!--        表示项目所有文件都要Filter-->
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ```

- 但是到这里还不够，若是有异常，还是可以事务提交成功，这是因为在BaseServlet中，也有一个异常被我们事先处理了，以至于到了这里便没有异常可处理了。所以我们要将BaseServlet中的异常也抛出。

  ```java
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      String action = request.getParameter("action");
      try {
          Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
          method.invoke(this, request, response);
      } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException(e);
      }
  }
  ```

- 这样们在产生订单的时候出现错误，控制台便会直接报错，或者数据库的数据还是正常的，不会因为错误而发生改动。

## Tomcat友好提示

- 在上面异常处理中，如果我们在代码中加入某段错误的代码，比如：int i=12/0;这一定是错误的，虽然我们让服务器处理了事务，但是别忘了，这是一句错误的代码，服务器还是会在网页中提示一堆代码错误的错在的，为了对用户友好一点，我们需要创建一个页面，将每次服务器出错的信息都包装好放在这个页面中。

- 首先，我们创建一个专门存放错误的包，然后创建一个产生错误信息jsp页面：

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: 黄渝斌
    Date: 2021/8/10
    Time: 18:07
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>服务器错误</title>
      <base href="http://localhost:8081/HBook/">
      <link rel="stylesheet" href="css/errorCss.css">
  </head>
  <body>
      <h1 class="error500Header">抱歉，服务器产生错误，程序猿正在加班加点修改中！</h1>
      <div class="error500">
          <a href="http://localhost:8081/HBook/">返回首页</a>
      </div>
  </body>
  </html>
  ```

- 下面是为其编写css样式单：

  ```css
  .error500Header,.error500 a{
      text-align: center;
  }
  .error500{
      position: absolute;
      left: 50%;top: 50%;
      transform: translate(-50%,-50%);
  }
  ```

- 要想服务器每次产生错误的时候访问到此页面，只能是去web.xml中配置相关文件。

  ```xml
  <error-page>
      <error-code>500</error-code>
      <location>/error/error500.jsp</location>
  </error-page>
  ```

  ```java
  package www.hyb.Json;
  
  import com.google.gson.Gson;
  import org.junit.Test;
  import www.hyb.Person.Person;
  
  public class jsonTest {
      @Test
      public void jsonToJavaBean(){
  //        获取一个Person对象1
          Person person = new Person(1,"hyb","男");
  //        获取一个Gson对象
          Gson gson = new Gson();
  //        将Person对象转换为Gson对象，利用toJson方法
          String s = gson.toJson(person);
          System.out.println(s);
  //          将Gson对象转换成一个Person对象，用fromJson方法
          Person person1 = gson.fromJson(s, Person.class);
          System.out.println(person1);
      }
  }
  ```

## AJAX

### 用户名是否合法

- 接下来我们使用AJAX和JSON做注册时的用户名已是否合法提示。

- 在提示的用户名是否正确的标签，加一个id选择器。

  ```jsp
  <td ><div class="RegisterAlert" id="RegisterAlert"></div></td>
  ```

  并且将利用request域提示的代码删除。

- 然后我们编写js代码，这些代码肯定是Jquery的，而且是一个Ajax请求。

  ```js
  $("#username").blur(function () {
      $.getJSON("http://localhost:8081/HBook/UserServlet","action=isRegister&username="+this.value,function (data) {
          if (data.legal){
              $("#RegisterAlert").text("用户名合法")
          }else{
              $("#RegisterAlert").text("用户名已存在")
          }
      })
  });
  ```

  代码的意思是，我们为带有id属性的这个输入框绑定失去焦点事件，一旦失去焦点，说明用户已经操作过一次，就立马请求Servlet，判断该用户名是否合法，若是合法与不合法，在function函数里显示，function函数里，text表示在警告标签里输入特定警告文字。

- 接下来我们编写Servlet程序

  ```java
      protected void isRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        获取请求参数
          String username = request.getParameter("username");
          boolean legal = userService.isLegal(username);
          HashMap<String, Object> map = new HashMap<>();
          map.put("legal",legal);
          Gson gson = new Gson();
          String s = gson.toJson(map);
          response.getWriter().write(s);
      }
  ```

### 加入购物车

- 在加入购物车之前，我们先将首页的jsp代码休整一下。

  ```jsp
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
  ```

  可见，我们抛弃了表单提交，这是要转为Jquery的AJAx请求提交，而且，form标签调整了位置，关于bookId隐藏域我们也不要了，因为首页的数据都遍历出来了，每次刷出一个数据，bookId的input隐藏域就会保存一次值，要是这样的话，我们就将所有数据传过去了。

  所以，我们要将bookId属性加入到submit提交按钮上，让其每次一提交，就产生一个bookId，也就传送一个bookId。

- 改完这个jsp，就是写js页面，在这之前，一定要链接这js文件才会生效。

  ```js
  $(".addCart").click(function () {
      // var bookId=parseInt($(".bookId").val());
      // var bookId=$(this).attr("bookId");
      // window.location.href="cartServlet?action=addItem&id="+bookId;
      let bookID=$(this).attr("bookId");
      $.getJSON("http://localhost:8081/HBook/cartServlet","action=addItem&bookId="+bookID,function (data) {
          $("#totalCount").text("您购物车里共有"+data.totalCount+"件商品");
          $("#cartings").text("您刚刚将"+data.lastName+"加入到购物车中！");
      })
  });
  ```

- 可见，我们的地址与之前的类似，为什么这么设定其Servlet？因为两种方法用的Servlet几乎都是一样，也就都要获取那几个属性，只不过是最后处理数据的方式不一样而已。

  ```java
      protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Integer bookId = Parse.StringParseInteger(request.getParameter("bookId"), 0);
          book book = bookService.queryBook(bookId);
          cartItem cartItem = new cartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
  
          /*
          * 不能直接new一个购物车，因为如果是同样的商品，就造成了数量不能相加
          * 所以要利用session来保存
          * */
  //        cart cart = new cart();
          cart cart = (cart) request.getSession().getAttribute("cart");
          if (cart==null){
              cart = new cart();
              request.getSession().setAttribute("cart",cart);
  
          }
          cart.addItem(cartItem);
          request.getSession().setAttribute("lastName",cartItem.getName());
  
  ////        重定向回原来地址
  //        response.sendRedirect(request.getHeader("Referer"));
  ////        request.getRequestDispatcher("/client/cart.jsp").forward(request,response);
          /*
          * 使用Ajax请求进行局部更新
          * */
          HashMap<String, Object> map = new HashMap<>();
          map.put("lastName",cartItem.getName());
          map.put("totalCount",cart.getTotalCount());
          Gson gson = new Gson();
          String s = gson.toJson(map);
          response.getWriter().write(s);
  
  
      }
  ```

- 在这里若是出现乱码的问题，可在BaseServlet中修改

  ```java
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html;charset=UTF-8");
  ```

  

