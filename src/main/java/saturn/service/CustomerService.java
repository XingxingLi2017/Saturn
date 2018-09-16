package saturn.service;

import saturn.model.Customer;

public interface CustomerService {
	void addCustomer(Customer customer);
	Customer getCustomerByUserName(String userName);
}
