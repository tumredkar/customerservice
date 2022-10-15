package com.tusharumredkar.customerservice.controller;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharumredkar.customerservice.common.CustomerServiceConstants;
import com.tusharumredkar.customerservice.exception.CustomerServiceException;
import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.repository.CustomerRepositoryDefinition;

@RestController
public class UpdateCustomer {

	@Autowired
	private CustomerRepositoryDefinition customerRepository;

	@PutMapping("/customers/{customerid}")
	private Object updateCustomer(@PathVariable("customerid") String customerid, @RequestBody Customer customer)
			throws CustomerServiceException {
		try {
			customer.setCustomerid(customerid);
			Customer customerDb = customerRepository.selectCustomer(customerid);
			if (Objects.isNull(customer)) {
				throw new CustomerServiceException(CustomerServiceConstants.CUSTOMER_NOT_FOUND);
			}
			BeanUtils.copyProperties(customer, customerDb);
			boolean update = customerRepository.updateCustomer(customerDb);
			if (!update) {
				throw new CustomerServiceException(CustomerServiceConstants.CUSTOMER_UPDATE_FAILED);
			}
			System.out.println("update successfull");
			return customerDb;
		} catch (CustomerServiceException cse) {
			cse.printStackTrace();
			throw cse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomerServiceException(CustomerServiceConstants.INTERAL_PROCESSING_ERROR);
		}
	}
}
