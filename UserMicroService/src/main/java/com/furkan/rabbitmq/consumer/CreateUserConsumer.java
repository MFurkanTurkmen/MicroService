package com.furkan.rabbitmq.consumer;

import com.furkan.rabbitmq.model.CreateUser;
import com.furkan.repository.entity.UserProfile;
import com.furkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queque-auth-create-user")
    public void createUserConsumerListener(CreateUser createUser){
        System.out.println("gelen mesajjjj..... "+createUser.toString());

        userProfileService.save(UserProfile.builder()
                        .authid(createUser.getAuthid())
                        .email(createUser.getEmail())
                        .username(createUser.getUsername())
                .build());
    }
}
