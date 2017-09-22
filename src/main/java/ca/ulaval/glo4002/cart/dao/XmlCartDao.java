package ca.ulaval.glo4002.cart.dao;

import ca.ulaval.glo4002.cart.application.CartList;
import ca.ulaval.glo4002.cart.application.PersistenceException;
import ca.ulaval.glo4002.cart.application.XmlUtils;
import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlCartDao implements CartDao{
    private static final String CART_STORAGE = "cart-storage";

    private static File storageFile;

    static {
        storageFile = XmlUtils.createStorageFile(CART_STORAGE);
    }

    @Override
    public Cart findOrCreateCartForClient(String email) {
        List<Cart> carts = readCartsFromFile();

        Cart cart = getCartByOwner(email, carts);

        return cart;
    }

    @Override
    public void addItemToCart(String email, ShopItem item) {
        System.out.println("Persistant Memory");
        List<Cart> carts = readCartsFromFile();
        Cart cart = getCartByOwner(email, carts);

        cart.addItem(new CartItem(item.getName(), 1));

        persistCarts(carts);
    }

    @Override
    public List<Cart> findAllCarts() {
        return this.readCartsFromFile();
    }

    private Cart getCartByOwner(String email, List<Cart> carts) {
        return carts.stream().filter(c -> c.ownerEmail.equals(email)).findFirst().orElseGet(() -> {
            Cart newCart = new Cart(email);
            carts.add(newCart);
            persistCarts(carts);
            return newCart;
        });
    }


    private void persistCarts(List<Cart> carts) {
        Marshaller marshaller = XmlUtils.createMarshaller();
        try {
            marshaller.marshal(new CartList(carts), storageFile);
        } catch (JAXBException e) {
            throw new PersistenceException(e);
        }
    }

    private List<Cart> readCartsFromFile() {
        Unmarshaller unmarshaller = XmlUtils.createUnmarshaller();
        try {
            return ((CartList) unmarshaller.unmarshal(storageFile)).getCarts();
        } catch (JAXBException e) {
            return new ArrayList<>();
        }
    }
}
