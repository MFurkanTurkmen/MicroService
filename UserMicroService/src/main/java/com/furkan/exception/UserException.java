package com.furkan.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private final EErrorType EErrorType;

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     * @param EErrorType
     */
    public UserException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public UserException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
