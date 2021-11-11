package www.hyb.test;

import org.junit.Test;
import www.hyb.pojo.cart;
import www.hyb.pojo.cartItem;
import www.hyb.service.impl.orderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class orderServiceImplTest {

    @Test
    public void createOrder() {
        cart cart = new cart();
        cart.addItem(new cartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        orderServiceImpl orderService = new orderServiceImpl();
        System.out.println(orderService.createOrder(cart, 1));
    }
}