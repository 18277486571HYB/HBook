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

        //        设置请求地址并保存
        page.setUrl("client/clientBookServlet?action=page");
        request.setAttribute("url",page.getUrl());

//        请求转发
        request.getRequestDispatcher("/client/index.jsp?action=page").forward(request,response);
    }

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

}
