package www.hyb.test;

import org.junit.Test;
import www.hyb.pojo.cart;
import www.hyb.pojo.cartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class cartTest {
    www.hyb.pojo.cart cart= new cart();

    @Test
    public void addItem() {
        cart.addItem(new cartItem(1,"六脉神剑",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new cartItem(1,"六脉神剑",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new cartItem(1,"神剑",1,new BigDecimal(10),new BigDecimal(40)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clearItem() {
    }

    @Test
    public void updateItemCount() {
    }
}