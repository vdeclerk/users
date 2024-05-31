package com.v15k.users.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.v15k.users.entities.User;
import com.v15k.users.entities.converter.UserConverter;
import com.v15k.users.entities.dto.PhoneDto;
import com.v15k.users.entities.dto.UserDto;

@DataJpaTest
class RepositoryIT {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PhoneRepository phoneRepo;

    @Test
    void testSave() {
        UserDto dto = new UserDto("name", "email@server.com", "password", 
            List.of(new PhoneDto("4321000", "366", "54")));
        User user = UserConverter.convert(dto, "token");
        userRepo.save(user);
        phoneRepo.save(user.getPhones().get(0));
        assertNotNull(user.getId());
        assertNotNull(user.getPhones().get(0).getId());
    }
}
