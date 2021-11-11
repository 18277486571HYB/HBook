package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.pojo.book;
import www.hyb.pojo.page;
import www.hyb.service.BookService;
import www.hyb.service.impl.BookServiceImpl;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    BookDao bookDao=new BookDaoImpl();
    BookService bookService=new BookServiceImpl();

    @Test
    public void countAll() {
        System.out.println(bookDao.countAll());
    }

    @Test
    public void countPresentDataNum() {
        for (book b:bookDao.countPresentDataNum(1,2)){
            System.out.println(b);
        }
    }
    @Test
    public void page(){
        System.out.println(bookService.page(1,page.PAGE_SIZE));
    }
}