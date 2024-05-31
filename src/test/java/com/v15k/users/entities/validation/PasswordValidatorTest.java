package com.v15k.users.entities.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

    @Test
    void testIsValid() {
        assertTrue("hunter2".matches("([a-z]*[0-9]*)+"));
    }
}
