package saturn.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import saturn.dao.CustomerDao;
import saturn.model.Authorities;
import saturn.model.Cart;
import saturn.model.Customer;
import saturn.model.User;
@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void addCustomer(Customer customer) {
		System.out.println("CustomerDaoImpl.addCustomer");
		customer.getUser().setEnabled(true);
		// set new register customer role
		Authorities authorities = new Authorities();
		authorities.setAuthorities("ROLE_USER");
		authorities.setEmailId(customer.getUser().getEmailId());
		
		Cart cart = new Cart();
		// mapping to each other
		customer.setCart(cart);
		cart.setCustomer(customer);
		
		// open hibernate session
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(authorities);
			session.save(customer);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	public Customer getCustomerByUserName(String userName) {
		System.out.println("CustomerDaoImpl.getCustomerByUserName");
		
		Session session = null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = (User) session.createCriteria(User.class)
					.add(Restrictions.eq("emailId", userName))
					.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		if (user != null) return user.getCustomer();
		return null;
	}

}
