package saturn.dao.impl;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import saturn.dao.CartDao;
import saturn.model.Cart;
import saturn.model.CartItem;
@Repository
public class CartDaoImpl implements CartDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Cart getCartById(int cartId) {
		Session session = null;
		Cart cart = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			cart = session.get(Cart.class, cartId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return cart;
	}

	public Cart validate(int cartId) throws IOException {
		Cart cart = getCartById(cartId);
		if (cart == null || cart.getCartItem().size() == 0) {
			throw new IOException(cartId+": invalidated cart, size=0 or non-existing");
		}
		update(cart);
		return cart;
	}

	public void update(Cart cart) {
		int cartId = cart.getId();
		double total = getSalesOrderTotal(cartId);
		cart.setTotalPrice(total);
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(cart);
		session.close();
	}
	
	/**
	 * Caculate total price of items in cart
	 * */
	private double getSalesOrderTotal(int cartId) {
		double total = 0;
		Cart cart = getCartById(cartId);
		List<CartItem> cartItems = cart.getCartItem();
		for(CartItem item: cartItems) {
			total += item.getPrice();
		}
		return total;
	}

}
