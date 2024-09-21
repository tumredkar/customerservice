package com.tusharumredkar.customerservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateUtilTest {

	@Value("${getAllEmployeeURI}")
	private String employeeEndpoint;
	
	@Autowired
	private HttpDefinition restHttpUtil;

	@Test
	public void test() {
		String result = restHttpUtil.makeHttpConnection(null, HttpMethod.GET, employeeEndpoint, "10000", "30000");
		System.out.println(result);

	}
}
