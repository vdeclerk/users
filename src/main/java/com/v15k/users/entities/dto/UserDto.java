package com.v15k.users.entities.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.v15k.users.entities.validation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {

    @Length(min = 2, max = 250)
    @NotBlank(message = "Name is mandatory")
    private final String name;

    @Length(min = 5, max = 1024)
    @Email
    @NotBlank(message = "Email is mandatory")
    private final String email;
    
    @Length(min = 2, max = 250)
    @NotBlank(message = "Password is mandatory")
    @Password
    private final String password;

    private final List<PhoneDto> phones;
}
