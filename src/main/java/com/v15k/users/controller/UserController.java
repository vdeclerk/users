package com.v15k.users.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v15k.users.entities.dto.UserCreatedDto;
import com.v15k.users.entities.dto.UserDto;
import com.v15k.users.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Log4j2
public class UserController {
    @Value("${base.uri}")
    private String baseUri;

    private final UserService userService;

    @Operation(summary = "Create user", description = "Creates users and returns the info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "The user was created"),
        @ApiResponse(responseCode = "400", description = "The user can't be validated"),
        @ApiResponse(responseCode = "409", description = "The user email is already used")
    })
    @PostMapping
    public ResponseEntity<UserCreatedDto> createUser(@Valid @RequestBody UserDto user) {
        UserCreatedDto dto = userService.createUser(user);
        return ResponseEntity.created(buildUri(dto.getId())).body(dto);
    }

    private URI buildUri(String id) {
        String uri = baseUri + "/user/" + id;
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            log.error("Error creating the user URI.\n URI: " + uri + "\n Error: " + e.getMessage());
        }
        return URI.create("");
    }
}
