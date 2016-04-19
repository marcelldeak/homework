
package hu.codingmentor.mobile.webshop.service;

import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class CartService {

    private final List<MobileDTO> cartList = new ArrayList();
    
    public CartService() {
    }

    public void addToCart(MobileDTO mobile){
        cartList.add(mobile);
    }
    
    public List<MobileDTO> getCart(){
        return cartList;
    }
    
    @Remove
    public void checkout(){
        cartList.clear();
    }
}
