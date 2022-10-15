package com.tusharumredkar.customerservice.repository;

import java.util.List;

import com.tusharumredkar.customerservice.model.Customer;

public interface EmployeeRepositoryDefinition {

	public boolean insertCustomer(Customer customer);

	public boolean deleteCustomer(String customerid);

	public 	boolean updateCustomer(Customer customer);

	public Customer selectCustomer(String customerid);

	public List<Customer> getAllCustomers();

}