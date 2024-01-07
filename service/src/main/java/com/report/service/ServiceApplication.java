package com.report.service;

import com.report.service.utils.HelperUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.report.service"})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(HelperUtil helperUtil, Environment environment) {
        return args -> {
            // Print the value of "skipDemoData"
            String skipDemoDataValue = environment.getProperty("skipDemoData");
            log.info("Value of skipDemoData: {}", skipDemoDataValue);

            // Check if the application property indicates to skip the demo data insertion
            if ("true".equals(skipDemoDataValue)) {
                log.info("Skipping demo data insertion.");
            } else {
                // Call insertDemoData method from HelperUtil
                helperUtil.insertDemoData();
            }
        };
    }
}
