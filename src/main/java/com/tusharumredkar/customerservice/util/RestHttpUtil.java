package com.tusharumredkar.customerservice.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(value = "http.util.enabled", havingValue = "false", matchIfMissing = true)
public class RestHttpUtil implements HttpDefinition {

	@Override
	public String makeHttpConnection(String requestBody, HttpMethod method, String url, String connectTimeout,
			String readTimeout) {
		try {
			RestTemplate restTemplate = new RestTemplateBuilder().build();
			ResponseEntity<String> response = restTemplate.exchange(url, method, new HttpEntity<String>(requestBody),
					String.class);
			System.out.println("Response code : " + response.getStatusCodeValue());
			System.out.println("Response from API : " + response.getBody());

			return response.getBody();
		} catch (RestClientException e) {
			System.out.println(e.getCause().getMessage());
			e.printStackTrace();
		}
		return null;

	}
}
