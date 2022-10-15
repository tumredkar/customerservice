package com.tusharumredkar.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class DeleteCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@DeleteMapping("/customers/{customerid}")
	private Customer updateCustomer(@PathVariable("customerid") String customerid) {
//		Customer customer = customerRepository.findById(customerid).get();
//		customerRepository.delete(customer);
		return null;
	}
}
