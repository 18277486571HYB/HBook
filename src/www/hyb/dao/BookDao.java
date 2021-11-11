package www.hyb.dao;

import www.hyb.pojo.book;

import java.util.List;

public interface BookDao {
//    添加书籍
    public int addBook(book book);
//    删除书籍
    public int delBookById(Integer id);
//    更新书籍
    public int updateBook(book book);
//    查询书籍
    public book queryBook(Integer id);
//    查询多本书籍
    public List<book> queryBooks();

    Integer countAll();

    List<book> countPresentDataNum(Integer pageNo,Integer pageSize);

    List<book> countPresentDataNumByPrice(int begin, Integer pageSize, Integer min, Integer max);

    Integer countAllByPrice(Integer min, Integer max);
}
