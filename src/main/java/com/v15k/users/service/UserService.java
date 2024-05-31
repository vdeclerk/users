package com.v15k.users.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v15k.users.entities.User;
import com.v15k.users.entities.converter.UserConverter;
import com.v15k.users.entities.dto.UserCreatedDto;
import com.v15k.users.entities.dto.UserDto;
import com.v15k.users.exception.UserExistException;
import com.v15k.users.repository.PhoneRepository;
import com.v15k.users.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PhoneRepository phoneRepo;
    private final JwtService jwtService;

    @Transactional
    public UserCreatedDto createUser(@Valid UserDto userDto) {
        if (userRepo.findByEmail(userDto.getEmail()).isPresent())
            throw new UserExistException();
        UserDetails details = new org.springframework.security.core.userdetails.User(
                userDto.getEmail(), 
                "", 
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        User user = UserConverter.convert(userDto, jwtService.generateToken(details));
        userRepo.save(user);
        user.getPhones().stream().forEach(phoneRepo::save);
        return UserConverter.convert(user);
    }
    
}
