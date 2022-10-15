package com.tusharumredkar.customerservice.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "http.util.enabled", havingValue = "true", matchIfMissing = true)
public class HttpUtil implements HttpDefinition {

	@Override
	public String makeHttpConnection(String requestBody, HttpMethod method, String url, String connectTimeout,
			String readTimeout) {
		try {
			HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

			// optional default is GET
			httpClient.setRequestMethod(method.toString());

			// add request header
			httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			// Send post request
			if (!HttpMethod.GET.equals(method) && requestBody != null) {
				httpClient.setDoOutput(true);
				try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
					wr.writeBytes(requestBody);
					wr.flush();
				}
			}

			int responseCode = httpClient.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + requestBody);
			System.out.println("Response Code : " + responseCode);

			try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

				String line;
				StringBuilder response = new StringBuilder();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}

				// print result
				System.out.println("Response From API : " + response.toString());

				return response.toString();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
