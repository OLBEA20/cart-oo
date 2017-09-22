package ca.ulaval.glo4002.cart.dao;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import java.util.List;

public interface CartDao {
    public Cart findOrCreateCartForClient(String email);
    public void addItemToCart(String email, ShopItem item);
    public List<Cart> findAllCarts();
}
