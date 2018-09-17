package saturn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import saturn.model.Cart;
import saturn.model.CartItem;
import saturn.model.Customer;
import saturn.model.Product;
import saturn.service.CartItemService;
import saturn.service.CartService;
import saturn.service.CustomerService;
import saturn.service.ProductService;

@Controller
public class CartItemController {
	@Autowired
	private CartService cartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/cart/add/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable(value="productId")int productId) {
		System.out.println("CartItemController.addCartItem");
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		System.out.println("loggedInUser is :"+username);
		
		Customer customer = customerService.getCustomerByUserName(username);
		Cart cart = customer.getCart();
		List<CartItem> cartItems = cart.getCartItem();
		Product product = productService.getProductById(productId);
		
		// traverse all items in cart to define add a new one or add quantity
		for (int i = 0 ; i < cartItems.size() ; i++) {
			CartItem cartItem = cartItems.get(i);
			if (product.getId() == (cartItem.getProduct().getId())) {
				cartItem.setQuantity(cartItem.getQuantity()+1);
				cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
				cartItemService.addCartItem(cartItem);
				return;
			}
		}
		
		// add new cart item
		CartItem cartItem = new CartItem();
		// construct relationships
		cartItem.setQuantity(1);
		cartItem.setProduct(product);
		cartItem.setPrice(product.getProductPrice());
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}
	
	@RequestMapping("/cart/removeCartItem/{cartItemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCartItem(@PathVariable(value = "cartItemId") int cartItemId) {
		System.out.println("CartItemController.removeCartItem");
		cartItemService.removeCartItem(cartItemId); 
	}
	
	@RequestMapping("/cart/removeAllItems/{cartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeAllCartItems(@PathVariable(value = "cartId") int cartId) {
		System.out.println("CartItemController.removeAllCartItems");
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);
	}
}
