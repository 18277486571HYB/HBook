package www.hyb.web;

import www.hyb.pojo.book;
import www.hyb.pojo.page;
import www.hyb.service.BookService;
import www.hyb.service.impl.BookServiceImpl;
import www.hyb.untils.Parse;
import www.hyb.untils.bean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

            response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");
        }else{
            request.getRequestDispatcher("/manager/book_edit.jsp?method=add").forward(request,response);
        }

    }

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
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取参数
        book parameterMap = bean.getParameterMap(request.getParameterMap(), new book());
//        调用更新方法
        bookService.updateBook(parameterMap);
//        重定向返回后台管理首页,并展示修改完的图书
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");

    }

    protected void getBook(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        获取参数
        Integer id = Parse.StringParseInteger(request.getParameter("id"), 0);
//        查询相应的图书
        book book = bookService.queryBook(id);
//        将图书保存到request域当中
        request.setAttribute("book",book);
//        请求转发，去到/manager/book_edit.jsp
        request.getRequestDispatcher("/manager/book_edit.jsp?method=update").forward(request,response);
    }

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


    /*
    * 该方法是page的Servlet，一旦单击了后台管理，会跳转至此页面
    * */

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取请求参数pageNo,pageSize
        Integer pageNo = Parse.StringParseInteger(request.getParameter("pageNo"), 1);
        Integer pageSize = Parse.StringParseInteger(request.getParameter("pageSize"), page.PAGE_SIZE);
//        获取一个page对象
        page<book> page = bookService.page(pageNo, pageSize);
//        将数据保存到request域当中
        request.setAttribute("page",page);

//        设置请求地址并保存
        page.setUrl("manager/bookServlet?action=page");
        request.setAttribute("url",page.getUrl());

//        请求转发
        request.getRequestDispatcher("/manager/manager_book.jsp?action=page").forward(request,response);
    }


}
