package com.report.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceApplicationTests {

	@Test
	void contextLoads() {
		// Set the system property to skip the demo data insertion
		System.setProperty("skipDemoData", "true");
	}

}
