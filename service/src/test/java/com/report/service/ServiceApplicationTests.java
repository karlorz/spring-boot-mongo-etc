package com.report.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ServiceApplicationTests {
	log.info("begin test");
	@Test
	void contextLoads() {
		// Set the system property to skip the demo data insertion
		System.setProperty("skipDemoData", "true");

		// Unset or reset the system property after the test
		System.clearProperty("skipDemoData");
	}

}
