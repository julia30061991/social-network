package com.publications.controller;

import com.publications.model.Activity;
import com.publications.model.ActivityType;
import com.publications.model.Publication;
import com.publications.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final AmqpTemplate amqpTemplate;

    public RestController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /* rest-ы исключительно для примера - оценить работу брокера RabbitMQ,
    проконтролировать процессы сериализации/десериализации объектов Publication и Activity */

    @PostMapping("/publication/new")
    public ResponseEntity<HttpStatus> createPublication(@RequestParam String content) {
        User user = new User(1, "Kenny_West", new ArrayList<>(), new ArrayList<>());
        Publication publication = new Publication(1, content, user);
        log.info("Send to queue publications");
        amqpTemplate.convertAndSend("new_publications", publication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/activity/new")
    public ResponseEntity<Object> createActivity(@RequestParam String type) {
        try {
            User user = new User(2, "Alis", new ArrayList<>(), new ArrayList<>());
            Activity activity = new Activity();
            activity.setId(2);
            activity.setUser(user);
            switch (type) {
                case "like":
                    activity.setType(ActivityType.ACTIVITY_LIKE);
                    amqpTemplate.convertAndSend("activity_like", activity);
                    log.info("Send to queue activity_like : " + activity);
                    break;
                case "post":
                    activity.setType(ActivityType.ACTIVITY_POST);
                    amqpTemplate.convertAndSend("activity_post", activity);
                    log.info("Send to queue activity_post : " + activity);
                    break;
                default:
                    throw new Exception("Does not found activity type. Choose \"like or post\"");
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}