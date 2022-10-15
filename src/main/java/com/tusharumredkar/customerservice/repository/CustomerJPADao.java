package com.tusharumredkar.customerservice.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.tusharumredkar.customerservice.model.Customer;

@Component
@ConditionalOnProperty(value = "jpa.enabled", havingValue = "true", matchIfMissing = true)
public class CustomerJPADao implements CustomerRepositoryDefinition {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public boolean insertCustomer(Customer customer) {
		customerRepository.save(customer);
		return true;
	}

	@Override
	public boolean deleteCustomer(String customerid) {
		customerRepository.deleteById(customerid);
		return true;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		Customer data = customerRepository.save(customer);
		return !Objects.isNull(data);
	}

	@Override
	public Customer selectCustomer(String customerid) {
		Optional<Customer> customer = customerRepository.findById(customerid);
		if (customer.isPresent()) {
			return customer.get();
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

}
