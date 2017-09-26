package ca.ulaval.glo4002.cart.application;

import ca.ulaval.glo4002.cart.dao.CartDao;
import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import javax.inject.Inject;
import java.util.List;

public class CartApplicationService {
	private CartDao cartDao;
	private ShopApplicationService shopApplicationService;

	@Inject
	public CartApplicationService(CartDao cartDao){
		this.cartDao = cartDao;
	}

	public Cart findOrCreateCartForClient(String email) {
		return cartDao.findOrCreateCartForClient(email);
	}

	public void addItemToCart(String email, ShopItem item) {
		cartDao.addItemToCart(email, item);
	}

	public List<Cart> findAllCarts(){
		return cartDao.findAllCarts();
	}

}
