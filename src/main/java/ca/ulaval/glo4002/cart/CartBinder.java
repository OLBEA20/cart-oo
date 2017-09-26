package ca.ulaval.glo4002.cart;

import ca.ulaval.glo4002.cart.application.CartApplicationService;
import ca.ulaval.glo4002.cart.application.Shop;
import ca.ulaval.glo4002.cart.application.ShopApplicationService;
import ca.ulaval.glo4002.cart.dao.CartDao;
import ca.ulaval.glo4002.cart.dao.VolatileCartDao;
import ca.ulaval.glo4002.cart.dao.XmlCartDao;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class CartBinder extends AbstractBinder {


    @Override
    protected void configure() {
        bind(CartApplicationService.class).to(CartApplicationService.class);
        bind(ShopApplicationService.class).to(ShopApplicationService.class);

        if(System.getProperty("store").equals("memory")) {
            bind(VolatileCartDao.class).to(CartDao.class);
        }
        else if(System.getProperty("store").equals("xml")) {
            bind(XmlCartDao.class).to(CartDao.class);
        }
    }
}
