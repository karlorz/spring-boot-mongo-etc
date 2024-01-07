package com.report.service;

import com.report.service.utils.HelperUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.report.service"})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

   @Bean
   CommandLineRunner runner(HelperUtil helperUtil) {
       return args -> {
           // Call insertDemoData method from HelperUtil
           helperUtil.insertDemoData();
       };
   }
}
