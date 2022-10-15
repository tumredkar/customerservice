package com.tusharumredkar.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class AddCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@PostMapping("/customers")
	private Customer addCustomer(@RequestBody Customer customer) {
		customerRepository.insertCustomer(customer);
		return customer;
	}
}
