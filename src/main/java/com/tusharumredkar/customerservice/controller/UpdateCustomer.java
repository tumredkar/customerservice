package com.tusharumredkar.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class UpdateCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@PutMapping("/customers/{customerid}")
	private Customer updateCustomer(@PathVariable("customerid") String customerid, @RequestBody Customer customer) {
		customer.setCustomerid(customerid);
		return null;
	}
}
