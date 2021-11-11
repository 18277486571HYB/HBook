package www.hyb.service;

import www.hyb.pojo.book;
import www.hyb.pojo.page;

import java.util.List;

public interface BookService {
    /*该Service是用户看见的层级，而DAO是我们对数据库操作的层级*/

    public void addBook(book book);

    public void delBook(Integer id);

    public void updateBook(book book);

    public book queryBook(Integer id);

    public List<book> queryBooks();

    page<book> page(Integer pageNo, Integer pageSize);

    page<book> PriceSearch(Integer pageNo, Integer pageSize, Integer min, Integer max);
}
