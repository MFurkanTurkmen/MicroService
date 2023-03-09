package com.furkan.service;

import com.furkan.dto.request.UserSaveRequestDto;
import com.furkan.dto.request.UserSaveResquestDto;
import com.furkan.exception.EErrorType;
import com.furkan.exception.UserException;
import com.furkan.mapper.IUserProfileMapper;
import com.furkan.repository.IUserProfileRepository;
import com.furkan.repository.entity.UserProfile;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    private final JwtTokenManager jwtTokenManager;
    public UserProfileService(IUserProfileRepository repository,
                              JwtTokenManager jwtTokenManager
    ){
        super(repository);
        this.repository=repository;
        this.jwtTokenManager = jwtTokenManager;
    }
    public Boolean saveDto(UserSaveResquestDto dto){
        UserProfile userProfile= IUserProfileMapper.INSTANCE.toUserProfile(dto);
        save(userProfile);
        return true;
    }

    public List<UserProfile> findAll(String token){
        Optional<Long> authid = jwtTokenManager.getIdFromToken(token);
        if(authid.isEmpty())
            throw new UserException(EErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(authid.get());
        if(userProfile.isEmpty())
            throw new UserException(EErrorType.INVALID_TOKEN,"Token için gönderilen kullanıcı sistemde kayıtlı değildir.");
        return findAll();
    }

    public void mesajAl(String mesaj){
        System.out.println("user servis ici mesaj... "+ mesaj);
    }


    @Cacheable(value = "getUpperCache")
    public String getUpperName(String name){
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }
        return name.toUpperCase();
    }
}

