package com.v15k.users.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.v15k.users.entities.dto.PhoneDto;
import com.v15k.users.entities.dto.UserDto;
import com.v15k.users.exception.UserExistException;

@SpringBootTest
class UserServiceIT {

    @Autowired
    UserService service;

    @Test
    void testCreateUser() {
        UserDto dto = new UserDto("name", "email@server.com", "password", 
            List.of(new PhoneDto("4321000", "366", "54")));
        service.createUser(dto);
        assertThrows(UserExistException.class, () -> service.createUser(dto));
    }
}
