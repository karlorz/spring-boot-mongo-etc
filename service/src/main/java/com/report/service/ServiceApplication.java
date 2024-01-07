package com.report.service;

import com.report.service.utils.HelperUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = {"com.report.service"})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(HelperUtil helperUtil, Environment environment) {
        return args -> {
            // Check if the system property or environment property indicates to skip the demo data insertion
            if (Boolean.parseBoolean(environment.getProperty("skipDemoData", "false"))) {
                // Call insertDemoData method from HelperUtil
                helperUtil.insertDemoData();
            }
        };
    }
}
