package com.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class NotifApp {
    public static void main(String[] args) {
        SpringApplication.run(NotifApp.class, args);
    }
}