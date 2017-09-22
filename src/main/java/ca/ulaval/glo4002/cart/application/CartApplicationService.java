package ca.ulaval.glo4002.cart.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ca.ulaval.glo4002.cart.dao.CartDao;
import ca.ulaval.glo4002.cart.dao.VolatileCartDao;
import ca.ulaval.glo4002.cart.dao.XmlCartDao;
import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public class CartApplicationService {
	private CartDao cartDao;

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
