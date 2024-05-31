package com.v15k.users.entities.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.v15k.users.entities.User;
import com.v15k.users.entities.dto.PhoneDto;
import com.v15k.users.entities.dto.UserDto;

class UserConverterTest {

    @Test
    void testConvertDto() {
        UserDto dto = new UserDto("name", "email", "password", 
                List.of(new PhoneDto("number", "city code", "country code")));
        User user = UserConverter.convert(dto, "token");
        assertEquals("name", user.getName());
        assertNotNull(user.getCreated());
        assertEquals(1, user.getPhones().size());
        assertEquals("city code", user.getPhones().get(0).getCityCode());
    }
}
