package com.tusharumredkar.customerservice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.tusharumredkar.customerservice.model.Customer;
import com.tusharumredkar.customerservice.util.DBConnectionManager;

@Component
@ConditionalOnProperty(value = "jpa.enabled", havingValue = "false", matchIfMissing = true)
public class CustomerJDBCDao implements CustomerRepositoryDefinition {

	private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, AGE, GENDER, COUNTRY) VALUES (?,?,?,?,?,?);";

	private static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE CUSTOMERID=?";

	private static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET FIRSTNAME=?, LASTNAME=?, AGE=?, GENDER=?, COUNTRY=? WHERE CUSTOMERID=?";

	private static final String SELECT_CUSTOMER_COMMON = "SELECT CUSTOMERID, FIRSTNAME, LASTNAME, AGE, GENDER, COUNTRY FROM CUSTOMER ";

	private static final String SELECT_CUSTOMER_BY_ID = SELECT_CUSTOMER_COMMON + " WHERE CUSTOMERID = ?";

	@Autowired
	private DBConnectionManager manager;

	@Override
	public boolean insertCustomer(Customer customer) {
		Connection connection = null;
		boolean insert = false;
		try {
			connection = manager.getConnection();
			PreparedStatement psmt = connection.prepareStatement(INSERT_CUSTOMER);
			int i = 1;
			psmt.setObject(i++, customer.getCustomerid());
			psmt.setObject(i++, customer.getFirstname());
			psmt.setObject(i++, customer.getLastname());
			psmt.setObject(i++, customer.getAge());
			psmt.setObject(i++, customer.getGender());
			psmt.setObject(i++, customer.getCountry());

			insert = psmt.execute();
			if (insert) {
				connection.commit();
			} else {
				connection.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection);
		}

		return insert;
	}

	@Override
	public boolean deleteCustomer(String customerid) {
		Connection connection = null;
		boolean insert = false;
		try {
			connection = manager.getConnection();
			PreparedStatement psmt = connection.prepareStatement(DELETE_CUSTOMER);
			int i = 1;
			psmt.setObject(i++, customerid);

			insert = psmt.execute();
			if (insert) {
				connection.commit();
			} else {
				connection.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection);
		}

		return insert;

	}

	@Override
	public boolean updateCustomer(Customer customer) {
		Connection connection = null;
		boolean insert = false;
		try {
			connection = manager.getConnection();
			PreparedStatement psmt = connection.prepareStatement(UPDATE_CUSTOMER);
			int i = 1;
			psmt.setObject(i++, customer.getFirstname());
			psmt.setObject(i++, customer.getLastname());
			psmt.setObject(i++, customer.getAge());
			psmt.setObject(i++, customer.getGender());
			psmt.setObject(i++, customer.getCountry());
			psmt.setObject(i++, customer.getCustomerid());

			insert = psmt.execute();
			if (insert) {
				connection.commit();
			} else {
				connection.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection);
		}

		return insert;

	}

	@Override
	public Customer selectCustomer(String customerid) {
		Connection connection = null;
		try {
			connection = manager.getConnection();
			PreparedStatement psmt = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
			int i = 1;
			psmt.setObject(i++, customerid);

			ResultSet resultSet = psmt.executeQuery();
			List<Customer> dataSet = populateCustomers(resultSet);
			if (dataSet != null && !dataSet.isEmpty()) {
				return dataSet.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection);
		}

		return null;

	}

	@Override
	public List<Customer> getAllCustomers() {
		Connection connection = null;
		try {
			connection = manager.getConnection();
			PreparedStatement psmt = connection.prepareStatement(SELECT_CUSTOMER_COMMON);

			ResultSet resultSet = psmt.executeQuery();
			return populateCustomers(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.closeConnection(connection);
		}

		return null;

	}

	private List<Customer> populateCustomers(ResultSet resultSet) throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		while (resultSet.next()) {
			int i = 1;
			Customer Customer = new Customer();
			// "SELECT CUSTOMERID, FIRSTNAME, LASTNAME, AGE, GENDER, COUNTRY FROM CUSTOMER";
			Customer.setCustomerid(resultSet.getString(i++));
			Customer.setFirstname(resultSet.getString(i++));
			Customer.setLastname(resultSet.getString(i++));
			Customer.setAge(resultSet.getInt(i++));
			Customer.setGender(resultSet.getString(i++));
			Customer.setCountry(resultSet.getString(i++));
			customers.add(Customer);
		}
		return customers;
	}
}
