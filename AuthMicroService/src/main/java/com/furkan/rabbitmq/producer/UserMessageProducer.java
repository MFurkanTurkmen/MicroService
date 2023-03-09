package com.furkan.rabbitmq.producer;

import com.furkan.rabbitmq.model.UserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(UserMessage userMessage){
        rabbitTemplate.convertAndSend("exchange-fanout-auth","key-auth",userMessage);
    }
}
