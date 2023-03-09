package com.furkan.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserMessage implements Serializable {
    String mesaj;
}
