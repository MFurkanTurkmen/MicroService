package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.model.UserMessage;
import com.furkan.repository.entity.UserProfile;
import com.furkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMessageConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queque-authdan-mesaj")
    public void createUserConsumerListener(UserMessage userMessage){
        System.out.println("gelen mesajjjj.....  consumer ici.... "+userMessage);

        userProfileService.mesajAl(userMessage.getMesaj().toString());
    }
}
