package saturn.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import saturn.dao.ProductDao;
import saturn.model.Product;
@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Product getProductById(int productId) {
		System.out.println("ProductDaoImpl.getProductById");
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			product = session.get(Product.class, productId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return product;
	}

	public void deleteProduct(int productId) {
		System.out.println("ProductDaoImpl.deleteProduct");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Product product = session.get(Product.class, productId);
			session.delete(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

	public void addProduct(Product product) {
		System.out.println("ProductDaoImpl.addProduct");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

	public void updateProduct(Product product) {
		System.out.println("ProductDaoImpl.updateProduct");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

	public List<Product> getAllProducts() {
		Session session = null;
		List<Product> products = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			products = session.createCriteria(Product.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return products;
	}

}
