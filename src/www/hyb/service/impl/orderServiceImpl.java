package www.hyb.service.impl;

import www.hyb.dao.BookDao;
import www.hyb.dao.impl.BookDaoImpl;
import www.hyb.dao.impl.orderDaoImpl;
import www.hyb.dao.impl.orderItemDaoImpl;
import www.hyb.dao.orderDao;
import www.hyb.dao.orderItemDao;
import www.hyb.pojo.*;
import www.hyb.service.orderService;

import java.util.Date;
import java.util.Map;

public class orderServiceImpl implements orderService {

    private final orderDao orderDao=new orderDaoImpl();
    private final orderItemDao orderItemDao=new orderItemDaoImpl();
    private final BookDao bookDao=new BookDaoImpl();

    /*
    * 创建订单
    * */
    @Override
    public String createOrder(cart cart, Integer userId) {
//        创建订单号，时间戳+用户Id
        String orderId= System.currentTimeMillis()+""+userId;
//        创建一个订单对象
        order order = new order(orderId,new Date(),cart.getTotalPrice(),0,userId);
//        保存订单
        orderDao.saveOrder(order);
//        从购物车里遍历数据到数据库
        for (Map.Entry<Integer, cartItem>entry:cart.getMap().entrySet()) {
//            获取每一个购物车中的商品项
            cartItem value = entry.getValue();
//            转换为每一个订单项
            orderItem orderItem = new orderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),orderId);
//            保存数据项到数据库
            orderItemDao.saveOrderItem(orderItem);
//            每生成订单一次，销量和库存对应变化
            book book = bookDao.queryBook(value.getId());
            book.setSales(book.getSales()+value.getCount());
            book.setStock(book.getStock()-value.getCount());
            bookDao.updateBook(book);
        }
//        清空购物车
        cart.clearItem();
        return orderId;
    }
}
