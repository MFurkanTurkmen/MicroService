package com.furkan.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

/**
 *  !!! DİKKAT !!!!
 * 1_bu bilgileri göndermek için serileştirmek gerekiyor çünkü
 * bu q üzerinde bilgiler şu şekil iletilir
 * bu java sınıfı serileştirilir ve base 64 şeklinde serileştirilir kaydedilir
 * bu kuyrugu olucak olan da bu serileştirmeyi alıp deserilize edip okur
 *
 * 2_ gönderilen sınıfı karşılayacak olan tanımlar paket adına kadar aynı olmalı
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUser implements Serializable {
    Long authid;
    String username;
    String email;
}
