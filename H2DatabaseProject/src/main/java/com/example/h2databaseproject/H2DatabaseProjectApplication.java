package com.example.h2databaseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
/*
        ---------------------------------------------
        SLPD
        System Layout Program Development
        ---------------------------------------------
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class H2DatabaseProjectApplication {

    public static void main(String[] args) {SpringApplication.run(H2DatabaseProjectApplication.class, args);}
}
