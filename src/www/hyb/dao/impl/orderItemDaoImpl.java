package www.hyb.dao.impl;


import www.hyb.dao.orderItemDao;
import www.hyb.pojo.orderItem;

public class orderItemDaoImpl extends BaseDao implements orderItemDao {
    @Override
    public int saveOrderItem(orderItem orderItem) {
        String sql="insert into hyb_order_item(name,count,price,total_price,orderId) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
