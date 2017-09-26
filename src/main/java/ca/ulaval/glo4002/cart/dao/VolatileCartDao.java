package ca.ulaval.glo4002.cart.dao;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolatileCartDao implements CartDao{
    private static HashMap<String, Cart> carts;
    static{ carts  = new HashMap<String, Cart>();}
    

    @Override
    public Cart findOrCreateCartForClient(String email) {

        if(carts.containsKey(email)){
            return carts.get(email);
        }
        else{
            carts.put(email, new Cart(email));
            return carts.get(email);
        }
    }

    @Override
    public void addItemToCart(String email, ShopItem item) {
        System.out.println("Volatile Memory");
        if(carts.containsKey(email)) {
            carts.get(email).addItem(new CartItem(item.getName(), 1));
        }
    }

    @Override
    public List<Cart> findAllCarts() {
        List<Cart> cartList = new ArrayList<Cart>(carts.values());
        return cartList;
    }

}
