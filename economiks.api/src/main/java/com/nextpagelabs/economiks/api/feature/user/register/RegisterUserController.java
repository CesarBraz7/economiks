package com.nextpagelabs.economiks.api.feature.user.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    @PostMapping
    public ResponseEntity<RegisteredUserResponse> register(@Valid @RequestBody RegisterUserRequest registerUserRequest){

        RegisteredUserResponse response = registerUserService.register(registerUserRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
