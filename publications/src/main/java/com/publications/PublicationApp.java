package com.publications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class PublicationApp {
    public static void main(String[] args) {
        SpringApplication.run(PublicationApp.class, args);
    }
}