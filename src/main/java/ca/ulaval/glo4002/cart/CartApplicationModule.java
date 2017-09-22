package ca.ulaval.glo4002.cart;

import ca.ulaval.glo4002.cart.dao.CartDao;
import ca.ulaval.glo4002.cart.dao.VolatileCartDao;
import ca.ulaval.glo4002.cart.dao.XmlCartDao;
import com.google.inject.AbstractModule;

public class CartApplicationModule extends AbstractModule{

    @Override
    protected void configure(){
        bind(CartDao.class).to(VolatileCartDao.class);
    }
}
