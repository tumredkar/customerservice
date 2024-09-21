package com.tusharumredkar.customerservice.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.common.CustomerServiceConstants;
import com.tusharumredkar.customerservice.exception.CustomerServiceException;
import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class DeleteCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@DeleteMapping("/customers/{customerid}")
	private Customer updateCustomer(@PathVariable("customerid") String customerid) throws CustomerServiceException {
		try {
			Customer customer = customerRepository.selectCustomer(customerid);
			if (Objects.isNull(customer)) {
				throw new CustomerServiceException(CustomerServiceConstants.CUSTOMER_NOT_FOUND);
			}
			customerRepository.deleteCustomer(customerid);
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
