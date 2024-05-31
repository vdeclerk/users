package com.v15k.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v15k.users.entities.dto.MessageDto;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity<MessageDto> home() {
        return ResponseEntity.ok(new MessageDto("Welcome to User creation API"));
    }
}
