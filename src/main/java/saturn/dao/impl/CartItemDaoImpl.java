package saturn.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import saturn.dao.CartItemDao;
import saturn.model.Cart;
import saturn.model.CartItem;
@Repository
public class CartItemDaoImpl implements CartItemDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void addCartItem(CartItem cartItem) {
		System.out.println("CartItemDaoImpl.addCartItem");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(cartItem);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
	}

	public void removeCartItem(int CartItemId) {
		System.out.println("CartItemDaoImpl.removeCartItem");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			CartItem cartItem = session.get(CartItem.class, CartItemId);
			Cart cart = cartItem.getCart();
			List<CartItem> cartItems = cart.getCartItem();
			cartItems.remove(cartItem);
			session.beginTransaction();
			session.delete(cartItem);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

	public void removeAllCartItems(Cart cart) {
		System.out.println("CartItemDaoImpl.removeAllCartItems");
		List<CartItem> cartItems = cart.getCartItem();
		for (CartItem cartItem : cartItems) {
			removeCartItem(cartItem.getId());
		}
	}

}
