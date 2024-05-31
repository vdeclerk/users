package com.v15k.users.entities.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserCreatedDto {
    
    private final UserDto user;
    private final String id;
    private final LocalDateTime created;
    private final LocalDateTime modified;
    private final LocalDateTime lastLogin;
    private final String token;
    private final Boolean isActive;

}
