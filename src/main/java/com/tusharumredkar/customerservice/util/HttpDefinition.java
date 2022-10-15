package com.tusharumredkar.customerservice.util;

import org.springframework.http.HttpMethod;

public interface HttpDefinition {

	public String makeHttpConnection(String requestBody, HttpMethod method, String url, String connectTimeout,
			String readTimeout);
}