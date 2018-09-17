package saturn.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import saturn.dao.SalesOrderDao;
import saturn.model.SalesOrder;
@Repository
public class SalesOrderDaoImpl implements SalesOrderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addSalesOrder(SalesOrder salesOrder) {
		System.out.println("SalesOrderDaoImpl.addSalesOrder");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(salesOrder);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}

}
