package com.subscriptions.controller;

import com.subscriptions.config.Receiver;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final AmqpTemplate amqpTemplate;

    public RestController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @PostMapping("/notification")
    public ResponseEntity<HttpStatus> sendNotification() {
        //вызов метода по проверке подписок пользователей
        if (Receiver.isIsNewMessage()) {
            String message = "We have a new publication or activity!";
            amqpTemplate.send("notification_subscribers", new Message(message.getBytes()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}