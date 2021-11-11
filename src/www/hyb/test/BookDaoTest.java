package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BaseDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.pojo.book;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {
     private final BookDao bookDao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new book(null,"星月神话","hyb",new BigDecimal("200.00"),100000,2000000,null));
    }

    @Test
    public void delBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new book(2,"大话西游","hyb",new BigDecimal("200.00"),100000,2000000,null));
    }

    @Test
    public void queryBook() {
        System.out.println(bookDao.queryBook(2));
    }

    @Test
    public void queryBooks() {
        for (book b:bookDao.queryBooks()) {
            System.out.println(b);
        }
    }
}