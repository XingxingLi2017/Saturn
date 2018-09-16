package saturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import saturn.model.Cart;
import saturn.model.Customer;
import saturn.service.CartService;
import saturn.service.CustomerService;

@Controller
public class CartController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "/cart/getCartById", method = RequestMethod.GET)
	public ModelAndView getCartId () {
		System.out.println("CartController.getCartId");
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		Customer customer = customerService.getCustomerByUserName(username);
		ModelAndView modelAndView = new ModelAndView("cart");
		modelAndView.addObject("cartId", customer.getCart().getId());
		return modelAndView;
	}
	
	@RequestMapping(value = "/cart/getCart/{cartId}",method = RequestMethod.GET)
	public @ResponseBody Cart getCartItems(@PathVariable(value="cartId")int cartId) {
		System.out.println("CartController.getCartItems");
		return cartService.getCartById(cartId);
	}
}
