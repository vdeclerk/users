package com.v15k.users.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.v15k.users.entities.dto.MessageDto;
import com.v15k.users.exception.UserExistException;

@ControllerAdvice
public class ExceptionHandlerConfig {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String fullMessage = ex.getLocalizedMessage();
        String substring = fullMessage.substring(fullMessage.lastIndexOf("default message"));
        String message = substring.substring(substring.indexOf('[') + 1, substring.indexOf(']'));
        return ResponseEntity.badRequest().body(new MessageDto(message));
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<MessageDto> handleValidationExceptions(UserExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageDto("El correo ya registrado"));
    }
}
