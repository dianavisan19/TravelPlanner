package com.example.travelplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={
        "com.example.travelplanner"})
//@EnableJpaRepositories(basePackages = "com.example.travelplanner.entity")

public class BudgetingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetingAppApplication.class, args);
        System.out.println("I m finally working");
    }

}
