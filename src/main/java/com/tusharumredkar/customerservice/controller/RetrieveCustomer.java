package com.tusharumredkar.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class RetrieveCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@GetMapping("/customers/{customerid}")
	private Customer getAllCustomers(@PathVariable(value = "customerid", required = true) String customerid) {
		System.out.println(customerid);
		return null;
	}
}
