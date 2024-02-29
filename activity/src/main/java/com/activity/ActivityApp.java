package com.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class ActivityApp {
    public static void main(String[] args) {
        SpringApplication.run(ActivityApp.class, args);
    }
}