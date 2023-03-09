package com.furkan.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbluserprofile")
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Kullanıcı kayıt olurken oluşan ve user,pass bilgileri tutulan
     * kayda ait eşleştirmede kullanılacaktır.
     */
    Long authid;
    String username;
    String email;
    String photo;
    String about;
    String phone;
    String age;
    String website;

}
