package www.hyb.pojo;

/*
* 购物车对象
* */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,cartItem> map=new LinkedHashMap<>();

    /*
    * 添加商品项
    * */

    public void addItem(cartItem cartItem){
        www.hyb.pojo.cartItem item = map.get(cartItem.getId());
        if (item==null){
//            之前没添加过
            map.put(cartItem.getId(),cartItem);
        }else{
//            之前添加过，数量加1，总价增加
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /*
    * 删除商品项
    * */

    public void deleteItem(Integer id){
        map.remove(id);
    }

    /*
    * 清空购物车
    * */

    public void clearItem(){
        map.clear();
    }

    /*
    * 修改商品数量
    * */

    public void updateItemCount(Integer id,Integer count){
        cartItem item = map.get(id);
        if (item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for (Map.Entry<Integer,cartItem> entry:map.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,cartItem> entry:map.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, cartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, cartItem> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", map=" + map +
                '}';
    }
}
