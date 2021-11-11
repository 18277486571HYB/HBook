package www.hyb.test;

import org.junit.Test;
import www.hyb.dao.impl.orderDaoImpl;
import www.hyb.dao.orderDao;
import www.hyb.pojo.order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class orderDaoImplTest {

    @Test
    public void saveOrder() {
        orderDao orderDao=new orderDaoImpl();
        orderDao.saveOrder(new order("111111",new Date(),new BigDecimal(100),0,1));
    }
}