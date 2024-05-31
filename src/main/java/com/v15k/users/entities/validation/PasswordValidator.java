package com.v15k.users.entities.validation;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Value("${password.regexp}")
    String regexp;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null)
            return false;
        return password.matches(regexp);
    }
    
}
