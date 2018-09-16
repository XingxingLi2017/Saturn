package saturn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saturn.dao.CartItemDao;
import saturn.model.Cart;
import saturn.model.CartItem;
import saturn.service.CartItemService;
@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemDao cartItemDao;
	public void addCartItem(CartItem cartItem) {
		System.out.println("CartItemServiceImpl.addCartItem");
		cartItemDao.addCartItem(cartItem);
	}

	public void removeCartItem(int CartItemId) {
		cartItemDao.removeCartItem(CartItemId);
	}

	public void removeAllCartItems(Cart cart) {
		cartItemDao.removeAllCartItems(cart);
	}

}
