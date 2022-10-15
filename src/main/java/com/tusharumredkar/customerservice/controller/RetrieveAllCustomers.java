package com.tusharumredkar.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class RetrieveAllCustomers {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@GetMapping("/customers")
	private List<Customer> getAllCustomers() {
		return null;
	}
}
