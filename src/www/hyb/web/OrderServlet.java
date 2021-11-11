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
