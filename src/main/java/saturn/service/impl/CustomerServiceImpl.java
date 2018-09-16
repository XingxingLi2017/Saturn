package saturn.service.impl;

import org.springframework.stereotype.Service;

import saturn.model.Customer;
import saturn.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	public void addCustomer(Customer customer) {
		System.out.println("CustomerServiceImpl.addCustomer");
	}

	public Customer getCustomerByUserName(String userName) {
		System.out.println("CustomerServiceImpl.getCustomerByUserName");
		return null;
	}

}
