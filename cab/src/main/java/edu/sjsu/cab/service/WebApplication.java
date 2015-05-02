package edu.sjsu.cab.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.sjsu.cab.bootstrap.CabConfiguration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({ CabConfiguration.class })
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        // ZillowPropertyDAOImpl zillowDao = (ZillowPropertyDAOImpl) context.getBean("zillowProperyDao");
    }
}
