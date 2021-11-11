package www.hyb.dao.impl;

import www.hyb.dao.orderDao;
import www.hyb.pojo.order;

import java.math.BigDecimal;

public class orderDaoImpl extends BaseDao implements orderDao {
    @Override
    public int saveOrder(order order) {
        String sql="insert into hyb_order(orderId,createTime,price,satatus,userId) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
