package com.notifications.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @RabbitListener(queues = "notification_subscribers")
    public void receivedMessage(Message message) {
        String info = message.toString();
        /* логика по обработке сообщения и определения, кому направить уведомление -> параметризированный
        метод, позволяющий задать получателя (пример: sendToSubscriber(User user, String email) */
        log.info("Received message" + info);
    }
}