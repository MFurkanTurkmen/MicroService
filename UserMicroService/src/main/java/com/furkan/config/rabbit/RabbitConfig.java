package com.furkan.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private String exchangeDirectAuth = "exchange-direct-auth";
    private String keyAuth = "key-auth";
    private String quequeAuthCreateUser = "queque-auth-create-user";

    private String fanout = "exchange-fanout-auth";
    private String authMessage= "queque-authdan-mesaj";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangeDirectAuth);
    }

    @Bean
    Queue createAuthUserQueue() {
        return new Queue(quequeAuthCreateUser);
    }

    @Bean
    Binding bindingCreateAuthUser(final Queue createAuthUserQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(createAuthUserQueue).to(directExchange).with(keyAuth);
    }


    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanout);
    }
    @Bean
    Queue fanoutMessage(){
        return new Queue(authMessage);
    }
    @Bean
    Binding bindingmessage(final Queue fanoutMessage, final FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutMessage).to(fanoutExchange);

    }
}
