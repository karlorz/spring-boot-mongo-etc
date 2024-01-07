package com.report.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestPropertySource(properties = {"skipDemoData=true"})
@Slf4j
class ServiceApplicationTests {

	@Test
	void contextLoads() {
		log.info("begin test");
		// Your test logic here
	}
}
