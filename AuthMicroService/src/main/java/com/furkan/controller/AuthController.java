package com.furkan.controller;

import com.furkan.dto.request.DoLoginRequestDto;
import com.furkan.dto.request.RegisterRequestDto;
import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import com.furkan.rabbitmq.producer.CreateUserProducer;
import com.furkan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static com.furkan.constants.RestEndPoints.*;


@RestController
@RequestMapping(API+VERSION+AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CreateUserProducer createUserProducer;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);

        authService.register(dto);
        return ResponseEntity.ok(true);
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }


    @GetMapping("/message")
    public ResponseEntity<String> message(){
        return ResponseEntity.ok("merhaba bu bir mesaj");
    }

    @PostMapping("/userlaramesajgonderpost")
    public ResponseEntity<String> useramesajpost(){
        authService.mesajgonder();
        return ResponseEntity.ok("controller response entity mesajı");
    }

    @GetMapping("/userlaramesajgonderget")
    public ResponseEntity<String> useramesajget(){
        authService.mesajgonder();
        return ResponseEntity.ok("controller response entity mesajı");
    }





















//    @Value("${bu-benim-tanimim.bunedirki}")
//    private String ifade;
//
//    @PostMapping(REGISTER)
//    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
//        if(!dto.getPassword().equals(dto.getRepassword()))
//            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
//        authService.register(dto);
//        return ResponseEntity.ok(true);
//    }
//    @PostMapping(LOGIN)
//    public ResponseEntity<String> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
//        return ResponseEntity.ok(authService.doLogin(dto));
//    }
//    @GetMapping("/message")
//    public ResponseEntity<String> getMessage(){
//        return ResponseEntity.ok(ifade);
//    }
}
