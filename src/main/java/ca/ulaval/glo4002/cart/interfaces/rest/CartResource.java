package ca.ulaval.glo4002.cart.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.cart.CartApplicationModule;
import ca.ulaval.glo4002.cart.application.CartApplicationService;
import ca.ulaval.glo4002.cart.application.ShopApplicationService;
import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Path("/clients/{" + CartResource.EMAIL_PARAMETER + "}/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
	public static final String EMAIL_PARAMETER = "email";
	public static final String SKU_PARAMETER = "sku";

	private CartApplicationService cartService;
	private ShopApplicationService shopService;

	public CartResource() {
		Injector injector = Guice.createInjector(new CartApplicationModule());
		this.cartService = injector.getInstance(CartApplicationService.class);
		this.shopService = new ShopApplicationService();
	}

	@GET
	public Cart getCart(@PathParam(EMAIL_PARAMETER) String email) {
		return cartService.findOrCreateCartForClient(email);
	}

	@PUT
	@Path("/{" + SKU_PARAMETER + "}")
	public Response addItemToCart(@PathParam(EMAIL_PARAMETER) String email, @PathParam(SKU_PARAMETER) String sku) {
		// TODO this resource does too much
		ShopItem shopItem = shopService.findItemBySku(sku);
		cartService.addItemToCart(email, shopItem);
		return Response.ok().build();
	}
}
