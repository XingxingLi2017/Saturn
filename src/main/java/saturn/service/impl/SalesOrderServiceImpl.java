package saturn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saturn.dao.SalesOrderDao;
import saturn.model.SalesOrder;
import saturn.service.SalesOrderService;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
	@Autowired
	private SalesOrderDao salesOrderDao;
	public void addSalesOrder(SalesOrder salesOrder) {
		salesOrderDao.addSalesOrder(salesOrder);
	}

}
