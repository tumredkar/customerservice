package com.tusharumredkar.customerservice.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBConnectionManager {

	@Autowired
	private DataSource dataSource;

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.rollback();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
