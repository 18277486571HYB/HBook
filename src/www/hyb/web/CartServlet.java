package www.hyb.web;

import com.google.gson.Gson;
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
import java.util.HashMap;

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

    protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath()+"/client/cart.jsp");
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Parse.StringParseInteger(request.getParameter("id"), 0);
        cart cart=(cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.deleteItem(id);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cart cart=(cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clearItem();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Parse.StringParseInteger(request.getParameter("id"), 0);
        Integer count = Parse.StringParseInteger(request.getParameter("count"), 1);
        if (count<0){
            count=0;
        }
        cart cart=(cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateItemCount(id,count);
        }
        response.sendRedirect(request.getHeader("Referer"));
    }
}
