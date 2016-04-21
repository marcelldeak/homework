package hu.codingmentor.mobile.webshop.service;

import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import hu.codingmentor.mobile.webshop.interceptor.Validate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class CartService implements Serializable{

    private final transient List<MobileDTO> cartList = new ArrayList<>();

    public CartService() {
        // Default constructor
    }

    @Validate
    public void addToCart(MobileDTO mobile) {
        cartList.add(mobile);
    }

    public List<MobileDTO> getCart() {
        return cartList;
    }

    @Remove
    public void checkout() {
        cartList.clear();
    }
}
