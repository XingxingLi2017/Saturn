package saturn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saturn.dao.CustomerDao;
import saturn.model.Customer;
import saturn.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	public void addCustomer(Customer customer) {
		System.out.println("CustomerServiceImpl.addCustomer");
		customerDao.addCustomer(customer);
	}

	public Customer getCustomerByUserName(String userName) {
		System.out.println("CustomerServiceImpl.getCustomerByUserName");
		return customerDao.getCustomerByUserName(userName);
	}

}
