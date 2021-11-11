package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.orderItemDaoImpl;
import www.hyb.dao.orderItemDao;
import www.hyb.pojo.orderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class orderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        orderItemDao orderItemDao=new orderItemDaoImpl();
        orderItemDao.saveOrderItem(new orderItem(null,"java",1,new BigDecimal(101),new BigDecimal(100),"111111"));
    }
}