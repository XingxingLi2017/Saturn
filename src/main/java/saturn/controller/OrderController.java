package saturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import saturn.model.Cart;
import saturn.model.Customer;
import saturn.model.SalesOrder;
import saturn.service.CartService;
import saturn.service.SalesOrderService;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;
	@Autowired
	private SalesOrderService customerOrderService;
	
	@RequestMapping(value="/order/{cartId}")
	public String createOrder(@PathVariable("cartId") int cartId) {
		SalesOrder salesOrder = new SalesOrder();
		Cart cart = cartService.getCartById(cartId);
		salesOrder.setCart(cart);
		Customer customer = cart.getCustomer();
		salesOrder.setCustomer(customer);
		salesOrder.setBillingAddress(customer.getBillingAddress());
		salesOrder.setShippingAddress(customer.getShippingAddress());
		customerOrderService.addSalesOrder(salesOrder);
		return "redirect:/checkout?cartId="+cartId;
	}
}
