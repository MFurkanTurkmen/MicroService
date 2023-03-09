package com.furkan.rabbitmq.producer;

import com.furkan.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createSendMessage(CreateUser createUser){
        rabbitTemplate.convertAndSend("exchange-direct-auth","key-auth",createUser);
    }
}
