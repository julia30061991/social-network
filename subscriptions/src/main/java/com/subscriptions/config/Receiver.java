package com.subscriptions.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publications.model.Publication;
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

    private static boolean isNewMessage = false;

    public static boolean isIsNewMessage() {
        return isNewMessage;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "publications")
    public void receivedMessage(Message message) throws Exception{
        Publication publication = mapper.readValue(message.getBody(), Publication.class);
        isNewMessage = true;
        log.info("Received message" + publication.toString());
    }
}