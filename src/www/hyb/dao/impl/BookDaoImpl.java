package www.hyb.dao.impl;

import www.hyb.dao.BookDao;
import www.hyb.pojo.book;

import java.util.List;

/*
* 注意：这个BaseDao是前面抽取的BaseDao
* */

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(book book) {
        String sql="insert into book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg());
    }

    @Override
    public int delBookById(Integer id) {
        String sql="delete from book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(book book) {
        String sql="update book set name=?,author=?,price=?,sales=?,stock=?,img_path=?where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg(),book.getId());
    }

    @Override
    public book queryBook(Integer id) {
        String sql="select id,name,author,price,sales,stock,img_path from book where id=?";
        return queryForOne(book.class,sql,id);
    }

    @Override
    public List<book> queryBooks() {
        String sql="select id,name,author,price,sales,stock,img_path from book";
        return queryList(book.class,sql);
    }

    @Override
    public Integer countAll() {
        String sql="select count(*) from book";

        /*
        * 这里我们调用查询某个值的方法，在BaseDao里
        * */
//        return (Integer) queryOneValue(sql);
        /*
        * 注意，像以上的写法是错误的，因为Object类型不能转换为Integer类型
        * */
        Number number =(Number) queryOneValue(sql);
        return number.intValue();
    }

    @Override
    public List<book> countPresentDataNum(Integer begin,Integer pageSize) {
        String sql="select id,name,author,price,sales,stock,img_path from book limit ?,?";
        return queryList(book.class,sql,begin,pageSize);
    }

    @Override
    public List<book> countPresentDataNumByPrice(int begin, Integer pageSize, Integer min, Integer max) {
        String sql="select id,name,author,price,sales,stock,img_path from book where price > ? and price < ? order by price limit ?,?";
        return queryList(book.class,sql,min,max,begin,pageSize);
    }

    @Override
    public Integer countAllByPrice(Integer min, Integer max) {
        String sql="select count(*) from book where price > ? and price < ?";
        Number number =(Number) queryOneValue(sql,min,max);
        return number.intValue();
    }
}
