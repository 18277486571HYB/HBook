package www.hyb.web;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.beanutils.BeanUtils;
import www.hyb.pojo.user;
import www.hyb.service.UserService;
import www.hyb.service.impl.UserServiceImpl;
import www.hyb.untils.bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{
    private final UserService userService=new UserServiceImpl();

    /*实现注册功能的代码*/
    protected void register(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //        获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("ConfirmPassword");
        String email = request.getParameter("email");
        String checkcode = request.getParameter("checkcode");

        user user= bean.getParameterMap(request.getParameterMap(), new user());
        System.out.println(user);

        /*
        * 获取验证码
        * */

//        从session中获取
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//        马上删除，防止第二次使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);



//        验证码是否错误
        if (token!=null&&token.equalsIgnoreCase(checkcode)){
//            检验用户名是否合法
            if (userService.isLegal(username)){
//                合法就判断确认密码是否正确
//
                if(password.equals(confirmPassword)){
                    www.hyb.pojo.user u = new user(null, username, password, email);
                    userService.RegisterUser(u);
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
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user user = new user(null, username, password, null);
        if (userService.Login(user)!=null){
            Integer userId = userService.getUserId(user);
            user.setId(userId);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/client/mainIndex.jsp").forward(request,response);
        }else {
            System.out.println("用户名或密码错误！");
            request.setAttribute("msg","用户名或密码错误！");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/static/Login.jsp").forward(request,response);
        }
    }


    /*
    * 实现注销功能
    * */
    protected void loginDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        消除session
        request.getSession().invalidate();
//        重定向到首页
        response.sendRedirect(request.getContextPath());
    }

    /*
    * 利用AJAX实现用户名是否可用
    * */

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
}
