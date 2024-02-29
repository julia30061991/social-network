package com.activity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publications.model.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver implements RabbitListenerConfigurer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = {"activity_like", "activity_post"})
    public void receivedMessage(Message message) throws Exception {
        Activity activity = mapper.readValue(message.getBody(), Activity.class);
        //логика по обработке сообщения и определения, кому направить уведомление
        log.info("Received message" + activity.toString());
    }
}