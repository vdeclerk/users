package com.v15k.users.entities.converter;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.v15k.users.entities.Phone;
import com.v15k.users.entities.User;
import com.v15k.users.entities.dto.PhoneDto;
import com.v15k.users.entities.dto.UserCreatedDto;
import com.v15k.users.entities.dto.UserDto;

public class UserConverter {

    private UserConverter() {}

    public static User convert(UserDto dto, String token) {
        String salt = BCrypt.gensalt();
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                   .name(dto.getName())
                   .email(dto.getEmail())
                   .password(BCrypt.hashpw(dto.getPassword(), salt))
                   .salt(salt)
                   .uuid(UUID.randomUUID())
                   .created(now)
                   .modified(now)
                   .lastLogin(now)
                   .token(token)
                   .isActive(true)
                   .build();
        user.addPhones(dto.getPhones().stream().map(UserConverter::convert).toList());
        return user;
    }

    private static Phone convert(PhoneDto dto) {
        return Phone.builder()
                    .number(dto.getNumber())
                    .cityCode(dto.getCitycode())
                    .countryCode(dto.getCountrycode())
                    .build();
    }

    private static PhoneDto convert(Phone phone) {
        return new PhoneDto(phone.getNumber(), phone.getCityCode(), phone.getCountryCode());
    }

    public static UserCreatedDto convert(User user) {
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), "", user.getPhones().stream().map(UserConverter::convert).toList());
        return new UserCreatedDto(userDto, 
                                  user.getUuid().toString(), 
                                  user.getCreated(), 
                                  user.getModified(), 
                                  user.getLastLogin(), 
                                  user.getToken(), 
                                  user.getIsActive());
    }

}
