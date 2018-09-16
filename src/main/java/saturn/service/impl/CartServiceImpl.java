package saturn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saturn.dao.CartDao;
import saturn.model.Cart;
import saturn.service.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;
	public Cart getCartById(int cartId) {
		System.out.println("CartServiceImpl.getCartById");
		return cartDao.getCartById(cartId);
	}

}
