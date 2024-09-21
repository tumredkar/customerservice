package com.tusharumredkar.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.common.CustomerServiceConstants;
import com.tusharumredkar.customerservice.exception.CustomerServiceException;
import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class AddCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) throws CustomerServiceException {
		try {
			boolean insert = customerRepository.insertCustomer(customer);
			if (!insert) {
				throw new CustomerServiceException(CustomerServiceConstants.CUSTOMER_CREATION_FAILED);
			}
			return customer;
		} catch (CustomerServiceException cse) {
			cse.printStackTrace();
			throw cse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomerServiceException(CustomerServiceConstants.INTERAL_PROCESSING_ERROR);
		}
	}
}
