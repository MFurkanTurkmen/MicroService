package com.furkan.service;


import com.furkan.dto.request.DoLoginRequestDto;
import com.furkan.dto.request.RegisterRequestDto;
import com.furkan.dto.request.UserSaveResquestDto;
import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import com.furkan.manager.IUserProfileManager;
import com.furkan.mapper.IAuthMapper;
import com.furkan.rabbitmq.model.CreateUser;
import com.furkan.rabbitmq.model.UserMessage;
import com.furkan.rabbitmq.producer.CreateUserProducer;
import com.furkan.rabbitmq.producer.UserMessageProducer;
import com.furkan.repository.IAuthRepository;
import com.furkan.repository.entity.Auth;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;

    private final JwtTokenManager jwtTokenManager;
    private final CreateUserProducer createUserProducer;
    private final UserMessageProducer userMessageProducer;

    private final IUserProfileManager userProfileManager;
    public AuthService(IAuthRepository repository, IUserProfileManager userProfileManager
            , JwtTokenManager jwtTokenManager, CreateUserProducer createUserProducer
            , UserMessageProducer userMessageProducer){
        super(repository);
        this.repository = repository;
        this.userProfileManager = userProfileManager;
        this.jwtTokenManager=jwtTokenManager;
        this.createUserProducer = createUserProducer;
        this.userMessageProducer = userMessageProducer;
    }
    public boolean register(RegisterRequestDto dto){
        if(repository.isUsername(dto.getUsername()))
            throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(dto));
        userProfileManager.save(UserSaveResquestDto.builder()
                .authid(auth.getId())
                .email(auth.getEmail())
                .username(auth.getUsername())
                .build());
//        createUserProducer.createSendMessage(CreateUser.builder()
//                .authid(auth.getId())
//                .email(auth.getEmail())        // RABBİT MQ İCİN BURASI
//                .username(auth.getUsername()).build());
        return true;
    }

    public String mesajgonder(){
        String mesaj="merhaba bu mesaj rabbit mq ile userlara iletilmistir";
        userMessageProducer.sendMessage(UserMessage.builder()
                        .mesaj(mesaj)
                .build());
        return "auth icinden ..... mesaj basarili sekilde iletildi sanirim";
    }


    public String doLogin(DoLoginRequestDto dto){
        Optional<Auth> auth =  repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthException(EErrorType.TOKEN_ERROR);
        return token.get();
    }







}
