package com.furkan.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallBackController {
    @GetMapping("/fallbackauth")
    public ResponseEntity<String> fallbackauth(){
        return ResponseEntity.ok("auth servis kapalı");
    }

    @GetMapping("/fallbackuser")
    public ResponseEntity<String> fallbackuser(){
        return ResponseEntity.ok("user servis kapalı");
    }


}
