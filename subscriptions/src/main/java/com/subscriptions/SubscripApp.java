package com.subscriptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class SubscripApp {
    public static void main(String[] args) {
        SpringApplication.run(SubscripApp.class, args);
    }
}