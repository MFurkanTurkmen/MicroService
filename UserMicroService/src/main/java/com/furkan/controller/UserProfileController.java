package com.furkan.controller;

import com.furkan.dto.request.BaseRequestDto;
import com.furkan.dto.request.UserSaveRequestDto;
import com.furkan.dto.request.UserSaveResquestDto;
import com.furkan.repository.entity.UserProfile;
import com.furkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import static com.furkan.constants.RestEndPoints.*;


@RestController
@RequestMapping(API+VERSION+USER)
@RequiredArgsConstructor
public class UserProfileController {
private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserSaveResquestDto dto){
        return ResponseEntity.ok(userProfileService.saveDto(dto));
    }


    @GetMapping(FINDALL)
    @ResponseBody
    public ResponseEntity<List<UserProfile>> getAll(@Valid BaseRequestDto dto){
        return ResponseEntity.ok(userProfileService.findAll(dto.getToken()));
    }

    @GetMapping("/getuppername")
    public ResponseEntity<String> getUpperCaseName(String name){
        return ResponseEntity.ok(userProfileService.getUpperName(name));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<Iterable<UserProfile>> findall(){
        return ResponseEntity.ok(userProfileService.findAll());
    }







}
