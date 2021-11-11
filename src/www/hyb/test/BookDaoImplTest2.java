package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.pojo.page;

import static org.junit.Assert.*;

public class BookDaoImplTest2 {

    private final BookDao bookDao=new BookDaoImpl();

    @Test
    public void countPresentDataNumByPrice() {
        System.out.println(bookDao.countPresentDataNumByPrice(0, page.PAGE_SIZE, 0, 10));
    }

    @Test
    public void countAllByPrice() {
        System.out.println(bookDao.countAllByPrice(0, 10));
    }
}