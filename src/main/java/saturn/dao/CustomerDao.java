package saturn.dao;

import saturn.model.Customer;

public interface CustomerDao {
	void addCustomer(Customer customer);
	Customer getCustomerByUserName(String userName);
}
