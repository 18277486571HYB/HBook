package www.hyb.service.impl;

import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.pojo.book;
import www.hyb.pojo.page;
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
}
